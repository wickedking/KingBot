package dao.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Get the number of users with higher xp per server
 * @author King
 *
 */
public class GetLevelForUsersAboveAction {
	
	private static final Logger logger = LogManager.getLogger(GetLevelForUsersAboveAction.class);
	
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
	 * @param xp 
	 * @return the number of users above
	 */
	public int execute(String guildID, int xp) {
		createSQL();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, guildID);
			ps.setInt(2, xp);
			ResultSet rs = ps.executeQuery();
			int result = -1;
			if(rs.isBeforeFirst()){
				rs.next();
				result = rs.getInt("number");
			}
			
			ps.close();
			return result;
			
		} catch (SQLException e) {
			logger.error(e);
			return -1;
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		
		
	}
	
	

}
