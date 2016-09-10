package dao.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Saves the logging channel for the server
 * @author King
 *
 */
public class InsertLoggingChanAction {
	
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
	public InsertLoggingChanAction(Connection connection){
		conn = connection;
	}
	
	/**
	 * Creates the sql to run
	 */
	private void createSql(){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" INSERT ");
		sb.append(" INTO ");
		sb.append(" T200_Logging ( ");
		sb.append(" T100_guildId, ");
		sb.append(" logging_chan ) ");
		sb.append(" VALUES ( ");
		sb.append(" ?, ");
		sb.append(" ? )");
				
		sql = sb.toString();
	}
	
	/**
	 * Executes the sql and return success of sql
	 * @param keyword
	 * @return
	 */
	public boolean execute(String guildId, String channelId){
		createSql();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, guildId);
			ps.setString(2, channelId);
			System.out.println(sql);
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
