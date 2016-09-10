package dao.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Get the number of users with higher xp per server
 * @author King
 *
 */
public class GetLevelForUsersAboveAction {
	
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
	public GetLevelForUsersAboveAction(Connection connection){
		conn = connection;
	}
	
	/**
	 * Creates the Sql needed
	 */
	private void createSQL(){
		StringBuilder sb = new StringBuilder();
		
		
		sb.append(" SELECT COUNT(*) AS number ");
		sb.append(" FROM T102_rank ");
		sb.append(" WHERE ");
		sb.append(" T100_guildId = ? ");
		sb.append(" AND xp > ? ");
		
		sql = sb.toString();
	}

	/**
	 * Executes the sql and returns the result
	 * @param guildID
	 * @return
	 */
	public int execute(String guildID, int xp) {
		// TODO Auto-generated method stub
		createSQL();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, guildID);
			ps.setInt(2, xp);
			ResultSet rs = ps.executeQuery();
			if(rs.isBeforeFirst()){
				rs.next();
				return  rs.getInt("number");
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
