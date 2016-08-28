package dao.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Keyword;

/**
 * Deletes the specified keyword from the database
 * @author King
 *
 */
public class DeleteKeywordAction {
	
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
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}


}
