package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.GuildLoggingBean;
import bean.Keyword;
import dao.action.DeleteKeywordAction;
import dao.action.DeleteLoggingChanAction;
import dao.action.GetAllServerInfoAction;
import dao.action.GetGuildMessageLoggingAction;
import dao.action.GetKeywordsByGuildAction;
import dao.action.GetLevelForUsersAboveAction;
import dao.action.GetLevelForUsersBelowAction;
import dao.action.GetLoggingChanAction;
import dao.action.GetRankForUserAction;
import dao.action.GetServerInfoAction;
import dao.action.InsertGuildMessageLoggingAction;
import dao.action.InsertLoggingChanAction;
import dao.action.PutKeywordAction;
import dao.action.UpdateGuildMessageLoggingAction;
import dao.action.UpdateXPAction;
import details.ServerInfo;
import hidden.HiddenConstants;

/**
 * DAO layer for the action class
 * @author King
 *
 */
public class BotDAO {
	
	/**
	 * Logger
	 */
	private static final Logger logger = LogManager.getLogger(BotDAO.class);

	/**
	 * default constructor
	 */
	public BotDAO(){
		//default constructor. no setup needed
	}

	/**
	 * returns a new connection for each request
	 * @return
	 */
	private Connection getConnection(){
		try {
			return DriverManager.getConnection(HiddenConstants.SQLLOGIN);
		} catch (SQLException e) {
			logger.error(e);
			return null;
		}
	}
	
	/**
	 * Get the info for all servers
	 */
	public Map<String, ServerInfo> getServersInfo(){
		GetAllServerInfoAction getAllServerAction = new GetAllServerInfoAction(getConnection());
		return getAllServerAction.execute();

	}
	
	/**
	 * Get the info for the specified server
	 * @param guildID
	 * @return ServerInfo The info for the given server
	 */
	public ServerInfo getServerInfo(String guildID){
		GetServerInfoAction getServerAction = new GetServerInfoAction(getConnection());
		return getServerAction.execute(guildID);
	}

	/**
	 * gets the users level
	 */
	public void getUserLevel(){
		//TODO implement
	}

	/**
	 * TODO
	 */
	public void getTopRankings(){
		//TODO implement
	}

	/**
	 * returns all keywords for guild
	 * @param guildID
	 * @return The list of keywords
	 */
	public List<Keyword> getKeywords(String guildID){
		GetKeywordsByGuildAction keywordAction = new GetKeywordsByGuildAction(getConnection());
		return keywordAction.execute(guildID);
	}

	/**
	 * Saves the keyword into the database
	 * 
	 * @param keyword
	 * @return Is successful
	 */
	public boolean saveKeyword(Keyword keyword){
		PutKeywordAction keywordAction = new PutKeywordAction(getConnection());
		return keywordAction.execute(keyword);
	}

	/**
	 * Deletes the Keyword
	 * 
	 * @param keyword
	 * @return Is successful
	 */
	public boolean deleteKeyword(Keyword keyword){
		DeleteKeywordAction delKeyAction = new DeleteKeywordAction(getConnection());
		return delKeyAction.execute(keyword);
	}

	/**
	 * Updates the xp of the user
	 * 
	 * @param guildID
	 * @param userId 
	 * @param xp 
	 */
	public void updateUserXP(String guildID, String userId, int xp){
		UpdateXPAction xpAction = new UpdateXPAction(getConnection());
		xpAction.execute(guildID, userId, xp);
	}
	
	/**
	 * Saves the logging channel for the server
	 * 
	 * @param guildId
	 * @param channelId
	 * @return boolean if successful
	 */
	public boolean setLoggingChannel(String guildId, String channelId){
		InsertLoggingChanAction loggingAction = new InsertLoggingChanAction(getConnection());
		return loggingAction.execute(guildId, channelId);
	}
	
	/**
	 * Returns the logging channel for server
	 * 
	 * @param guildId
	 * @return The logging channel id
	 */
	public String getLoggingChannel(String guildId){
		GetLoggingChanAction loggingAction = new GetLoggingChanAction(getConnection());
		return loggingAction.execute(guildId);
	}
	
	/**
	 * Deletes the set logging channel saved
	 * 
	 * @param guildId
	 * @return boolean if successful
	 */
	public boolean deleteLoggingChannel(String guildId){
		DeleteLoggingChanAction loggingAction = new DeleteLoggingChanAction(getConnection());
		return loggingAction.execute(guildId);
	}

	/**
	 * Returns the xp for the given user in the given server
	 * 
	 * @param guildId the guildId
	 * @param userId
	 * @return
	 */
	public String getXPForUser(String guildId, String userId){
		GetRankForUserAction getUserAction = new GetRankForUserAction(getConnection());
		int xp = getUserAction.execute(userId);
		GetLevelForUsersAboveAction getLevelAboveAction = new GetLevelForUsersAboveAction(getConnection());
		int countAbove = getLevelAboveAction.execute(guildId, xp) + 1;
		GetLevelForUsersBelowAction getLevelBelowAction = new GetLevelForUsersBelowAction(getConnection());
		int totalCount = getLevelBelowAction.execute(guildId);
		
		if(xp == -1 || countAbove == -1 || totalCount == -1){
			return "XP check failed";
		}
		return "Xp: " + xp + " Rank: " + countAbove + "/" + totalCount;
	}
	
	/**
	 * 
	 * @param guildId
	 * @param hour
	 * @param numMessages
	 * @return
	 */
	private boolean insertLoggingForGuild(String guildId, int hour, int numMessages){
		InsertGuildMessageLoggingAction insertAction = new InsertGuildMessageLoggingAction(getConnection());
		return insertAction.execute(guildId, hour, numMessages);
	}
	
	/**
	 * 
	 * @param loggingBean
	 * @return
	 */
	private boolean insertLoggingForGuild(GuildLoggingBean loggingBean){
		return insertLoggingForGuild(loggingBean.getGuildId(), loggingBean.getHour(), loggingBean.getNumMessages());
	} 
	
	/**
	 * 
	 * @param loggingBean
	 * @return
	 */
	public boolean updateLoggingForGuild(GuildLoggingBean loggingBean){
		GuildLoggingBean loggingBeanFromDb = getLoggingForGuild(loggingBean.getGuildId(), loggingBean.getHour());
		boolean success;
		if(loggingBeanFromDb == null){
			success = insertLoggingForGuild(loggingBean);
		} else {
			UpdateGuildMessageLoggingAction updateAction = new UpdateGuildMessageLoggingAction(getConnection());
			success = updateAction.execute(loggingBean);
		}
		return success;
	}
	
	/**
	 * 
	 * @param guildId
	 * @param hour
	 * @return
	 */
	public GuildLoggingBean getLoggingForGuild(String guildId, int hour){
		GetGuildMessageLoggingAction getLoggingAction = new GetGuildMessageLoggingAction(getConnection());
		//GuildLoggingBean bean = 
		return getLoggingAction.execute(guildId, hour);
		//return bean;
	}
	
	
}
