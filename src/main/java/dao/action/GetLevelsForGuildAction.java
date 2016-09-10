package dao.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * BROKEN PLEASE FIX
 * TODO
 * @author King
 *
 */
public class GetLevelsForGuildAction {
	
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
	public GetLevelsForGuildAction(Connection connection){
		conn = connection;
	}
	
	/**
	 * Creates the Sql needed
	 */
	private void createSQL(){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" SELECT XP ");
		sb.append("	FROM T102_rank "); 
		sb.append("	WHERE "); 
		sb.append("	T100_guildId = ? ");
		
		sql = sb.toString();
	}

	/**
	 * Executes the sql and returns the result
	 * @param guildID
	 * @return
	 */
	public int execute(String userID) {
		// TODO Auto-generated method stub
		createSQL();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userID);
			ResultSet rs = ps.executeQuery();
			return rs.getInt("XP");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
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
