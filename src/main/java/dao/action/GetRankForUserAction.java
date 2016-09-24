package dao.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Returns the xp rank for the user 
 * @author King
 *
 */
public class GetRankForUserAction {
	
	private static final Logger logger = LogManager.getLogger(GetRankForUserAction.class);
	
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
		createSQL();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userID);
			ResultSet rs = ps.executeQuery();
			int result = -1;
			if(rs.isBeforeFirst()){
				rs.next();
				result = rs.getInt("XP");
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
