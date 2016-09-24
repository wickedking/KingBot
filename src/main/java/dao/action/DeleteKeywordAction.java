package dao.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.Keyword;

/**
 * Deletes the specified keyword from the database
 * @author King
 *
 */
public class DeleteKeywordAction {
	
	private static final Logger logger = LogManager.getLogger(DeleteKeywordAction.class);
	
	/**
	 * The sql to run
	 */
	private String sql;
	
	/**
	 * The connection
	 */
	private Connection conn;
	
	/**
	 * The constructor
	 * @param connection
	 */
	public DeleteKeywordAction(Connection connection){
		conn = connection;
	}
	
	/**
	 * Creates the sql to run
	 */
	private void createSql(){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" DELETE ");
		sb.append(" FROM `T101_key_words` ");
		sb.append(" WHERE T100_guildId = ? ");
		sb.append(" AND keyword = ? ");
		
		sql = sb.toString();
	}
	
	/**
	 * Executes the sql and return success of sql
	 * @param keyword
	 * @return
	 */
	public boolean execute(Keyword keyword){
		createSql();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, keyword.getGuildId());
			ps.setString(2, keyword.getKeyword());
			ps.execute();
			ps.close();
			return true;
			
		} catch (SQLException e) {
			logger.warn(e);
			return false;
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.warn(e);
			}
		}
		
		
	}


}
