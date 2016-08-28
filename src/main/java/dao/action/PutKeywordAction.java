package dao.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import bean.Keyword;

/**
 * Inserts new keyword into database
 * @author King
 *
 */
public class PutKeywordAction {
	
	/**
	 * The sql to run
	 */
	private String sql;
	
	/**
	 * The connection
	 */
	private Connection conn;
	
	/**
	 * 
	 * @param connection The connection
	 */
	public PutKeywordAction(Connection connection){
		conn = connection;
	}
	
	/**
	 * Creates the Sql needed
	 */
	private void createSQL(){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" INSERT ");  
		sb.append(" INTO T101_key_words (");
		sb.append(" T100_guildId, ");
		sb.append(" keyword, ");
		sb.append(" action1, ");
		sb.append(" action2, ");
		sb.append(" message) ");
		sb.append(" VALUES ( ");
		sb.append(" ? ,");
		sb.append(" ? ,");
		sb.append(" ? ,");
		sb.append(" ? ,");
		sb.append(" ? ");
		sb.append(" ) ");
		
		sql = sb.toString();
		System.out.println(sql);
	}

	/**
	 * Executes the sql and returns the result
	 * @param guildID
	 * @return
	 */
	public boolean execute(Keyword keyword) {
		// TODO Auto-generated method stub
		createSQL();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, keyword.getGuildId());
			ps.setString(2, keyword.getKeyword());
			ps.setString(3, keyword.getAction1());
			ps.setString(4, keyword.getAction2());
			ps.setString(5, keyword.getMessage());
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
