package dao;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import bean.Keyword;
import dao.action.DeleteKeywordAction;
import dao.action.GetAllServerInfoAction;
import dao.action.GetKeywordsByGuildAction;
import dao.action.GetServerInfoAction;
import dao.action.PutKeywordAction;
import details.ServerInfo;

/**
 * DAO layer for the action class
 * @author King
 *
 */
public class BotDAO {

	/**
	 * default constructor
	 */
	public BotDAO(){

	}

	/**
	 * returns a new connection for each request
	 * @return
	 */
	private Connection getConnection(){
		try {
			return DriverManager.getConnection("");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	 * Get the info for the specifed server
	 */
	public ServerInfo getServerInfo(String guildID){
		GetServerInfoAction getServerAction = new GetServerInfoAction(getConnection());
		return getServerAction.execute(guildID);
	}

	/**
	 * gets the users level
	 */
	public void getUserLevel(){

	}

	/**
	 * TODO
	 */
	public void getTopRankings(){

	}

	/**
	 * returns all keywords for guild
	 * @param guildID
	 * @return
	 */
	public List<Keyword> getKeywords(String guildID){
		GetKeywordsByGuildAction keywordAction = new GetKeywordsByGuildAction(getConnection());
		return keywordAction.execute(guildID);
	}

	/**
	 * Save the created keyword
	 */
	public boolean saveKeyword(Keyword keyword){
		PutKeywordAction keywordAction = new PutKeywordAction(getConnection());
		return keywordAction.execute(keyword);
	}

	/**
	 * Delete the specified keyword
	 */
	public boolean deleteKeyword(Keyword keyword){
		DeleteKeywordAction delKeyAction = new DeleteKeywordAction(getConnection());
		return delKeyAction.execute(keyword);
	}

	/**
	 * TODO
	 */
	public void updateUserXP(){

	}

}
