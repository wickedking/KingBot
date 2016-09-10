package dao.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Returns the xp rank for the user 
 * @author King
 *
 */
public class GetRankForUserAction {
	
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
	public GetRankForUserAction(Connection connection){
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
		sb.append("	userId = ? ");
		//TODO add guildId as well
		
		sql = sb.toString();
	}

	/**
	 * Executes the sql and returns the result
	 * @param userID
	 * @return the xp of the user
	 */
	public int execute(String userID) {
		// TODO Auto-generated method stub
		createSQL();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userID);
			ResultSet rs = ps.executeQuery();
			if(rs.isBeforeFirst()){
				rs.next();
				return rs.getInt("XP");
			} else {
				return -1;
			}
			
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
