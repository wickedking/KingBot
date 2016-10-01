package dao.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Deletes the logging channel for specified server
 * @author King
 *
 */
public class DeleteLoggingChanAction {
	
	private static final Logger logger = LogManager.getLogger(DeleteLoggingChanAction.class);
	
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
	public DeleteLoggingChanAction(Connection connection){
		conn = connection;
	}
	
	/**
	 * Creates the sql to run
	 */
	private void createSql(){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" DELETE ");
		sb.append(" FROM ");
		sb.append(" T200_Logging");
		sb.append(" WHERE ");
		sb.append(" T100_guildId = ?");

		sql = sb.toString();
	}
	
	/**
	 * Executes the sql and return success of sql
	 * @param guildId The guildId
	 */
	public boolean execute(String guildId){
		createSql();
		logger.warn(sql);
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, guildId);
			int rows = ps.executeUpdate();
			ps.close();
			if(rows > 0){
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			logger.error(e);
			return false;
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		
		
	}


}
