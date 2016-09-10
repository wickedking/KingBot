package dao.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Returns the logging channel for the server
 * @author King
 *
 */
public class GetLoggingChanAction {
	
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
	public GetLoggingChanAction(Connection connection){
		conn = connection;
	}
	
	/**
	 * Creates the sql to run
	 */
	private void createSql(){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" SELECT ");
		sb.append(" logging_chan ");
		sb.append(" FROM ");
		sb.append(" T200_Logging ");
		sb.append(" WHERE ");
		sb.append(" T100_guildId = ? ");

		sql = sb.toString();
	}
	
	/**
	 * Executes the sql and return success of sql
	 * @param keyword
	 * @return
	 */
	public String execute(String guildId){
		createSql();
		System.out.println(sql);
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, guildId);
			ResultSet rs = ps.executeQuery();
			if(!rs.isBeforeFirst()){
				return null;
			} else {
				rs.next();
				return rs.getString("logging_chan");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
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
