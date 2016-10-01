package dao.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.Keyword;
import constants.BotConstants;
import details.ServerInfo;

/**
 * Deletes the specified keyword from the database
 * @author King
 *
 */
public class GetAllServerInfoAction {
	
	private static final Logger logger = LogManager.getLogger(GetAllServerInfoAction.class);
	
	/**
	 * The Sql to run
	 */
	private String sql;
	
	/**
	 * The connection
	 */
	private Connection conn;
	
	/**
	 * Default constructor
	 * @param connection
	 */
	public GetAllServerInfoAction(Connection connection){
		conn = connection;
	}
	
	/**
	 * creates the sql
	 */
	private void createSql(){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" SELECT T100.T100_guildId, ");
		sb.append(" T101.keyword, ");
		sb.append(" T101.action1, ");
		sb.append(" T101.action2, ");
		sb.append(" T101.message ");
		sb.append(" FROM ");
		sb.append(" T100_servers T100 ");
		sb.append(" LEFT OUTER JOIN ");
		sb.append(" T101_key_words T101 ");
		sb.append(" ON ");
		sb.append(" T100.T100_guildId = T101.T100_guildId ");
		sb.append(" ORDER BY T100.T100_guildId ");
		
		sql = sb.toString();
	}
	
	/**
	 * Execute the sql and returns the server infos
	 * @return
	 */
	public Map<String, ServerInfo> execute(){
		createSql();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			HashMap<String, ServerInfo> servers = new HashMap<>();
			while(rs.next()){
				ServerInfo server;
				if(servers.containsKey(rs.getString(BotConstants.GUILD_ID))){
					server = servers.get(rs.getString(BotConstants.GUILD_ID));
				} else {
					server = new ServerInfo();
					server.setGuildId(rs.getString(BotConstants.GUILD_ID));
				}
				Keyword keyword = new Keyword();
				keyword.setKeyword(rs.getString("keyword"));
				if(keyword.getKeyword() != null){
					keyword.setAction1(rs.getString("action1"));
					keyword.setAction2(rs.getString("action2"));
					keyword.setMessage(rs.getString("message"));
					
					List<Keyword> keywordList = server.getKeywords();
					keywordList.add(keyword);
					server.setKeywords(keywordList);
				}
				
				servers.put(server.getGuildId(), server);
			}
			
			ps.close();
			return servers;
			
		} catch (SQLException e) {
			logger.error(e);
			return null;
		} finally{
			try {
				if(conn != null && !conn.isClosed()){
					conn.close();
				}
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		
		
	}


}
