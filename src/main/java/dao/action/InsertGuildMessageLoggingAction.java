package dao.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Insert guild into table for logging
 * 
 * @author King
 *
 */
public class InsertGuildMessageLoggingAction {
	
	
	private static final Logger logger = LogManager.getLogger(InsertGuildMessageLoggingAction.class);
	
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
	public InsertGuildMessageLoggingAction(Connection connection){
		conn = connection;
	}
	
	/**
	 * Creates the sql to run
	 */
	private void createSql(){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" INSERT INTO ");
		sb.append(" T300_stats (");
		sb.append(" T100_guildId, ");
		sb.append(" hour, ");
		sb.append(" total_messages)");
		sb.append(" VALUES (");
		sb.append(" ? ,");
		sb.append(" ? ,");
		sb.append(" ? ,");
		sb.append(" ) ");

		sql = sb.toString();
	}
	
	
	/**
	 * 
	 * @param guildId
	 * @param hour
	 * @param numMessages
	 * @return
	 */
	public boolean execute(String guildId, int hour, int numMessages){
		createSql();
		logger.warn(sql);
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, guildId);
			ps.setInt(2, hour);
			ps.setInt(3, numMessages);
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
