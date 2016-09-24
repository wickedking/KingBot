package dao.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * BROKEN PLEASE FIX
 * TODO
 * @author King
 *
 */
public class GetLevelsForGuildAction {
	
	private static final Logger logger = LogManager.getLogger(GetLevelForUsersBelowAction.class);
	
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
	 * @param userID
	 * @return
	 */
	public int execute(String userID) {
		createSQL();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userID);
			ResultSet rs = ps.executeQuery();
			int result = rs.getInt("XP");
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
