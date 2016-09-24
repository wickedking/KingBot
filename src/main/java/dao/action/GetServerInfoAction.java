package dao.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.Keyword;
import details.ServerInfo;

/**
 * Deletes the specified keyword from the database
 * @author King
 *
 */
public class GetServerInfoAction {
	
	private static final Logger logger = LogManager.getLogger(GetServerInfoAction.class);
	
	/**
	 * The Sql to run
	 */
	private String sql;
	
	/**
	 * The connection
	 */
	private Connection conn;
	
	/**
	 * Constructor
	 * @param connection
	 */
	public GetServerInfoAction(Connection connection){
		conn = connection;
	}
	
	/**
	 * Creates the Sql
	 */
	private void createSql(){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" SELECT T100.T100_guildId, ");
		sb.append(" T101.keyword, ");
		sb.append(" T101.action1, ");
		sb.append(" T101.action2, ");
		sb.append(" T101.message ");
		sb.append(" FROM ");
		sb.append(" T100_servers T100, ");
		sb.append(" T101_key_words T101 ");
		sb.append(" WHERE ");
		sb.append(" T100.T100_guildId = T101.T100_guildId ");
		sb.append(" AND T100.T100_guildId = ? ");
				
		sql = sb.toString();
	}
	
	/**
	 * Execute the sql and returns the Server info
	 * @param guildId
	 * @return
	 */
	public ServerInfo execute(String guildId){
		createSql();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, guildId);
			ResultSet rs = ps.executeQuery();
			ServerInfo server = new ServerInfo();
			server.setGuildId(guildId);
			List<Keyword> keywords = new ArrayList<>();
			
			while(rs.next()){
				Keyword keyword = new Keyword();
				keyword.setKeyword(rs.getString("keyword"));
				keyword.setAction1(rs.getString("action1"));
				keyword.setAction2(rs.getString("action2"));
				keyword.setMessage(rs.getString("message"));
				keywords.add(keyword);
			}
			server.setKeywords(keywords);
			ps.close();
			return server;
			
		} catch (SQLException e) {
			logger.error(e);
			return null;
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		
		
	}


}
