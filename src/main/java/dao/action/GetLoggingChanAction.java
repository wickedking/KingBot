package dao.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Returns the logging channel for the server
 * @author King
 *
 */
public class GetLoggingChanAction {
	
	private static final Logger logger = LogManager.getLogger(GetLoggingChanAction.class);
	
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
	 * @param guildId
	 * @return the logging channel
	 */
	public String execute(String guildId){
		createSql();
		logger.warn(sql);
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, guildId);
			ResultSet rs = ps.executeQuery();
			String result = null;
			if(rs.isBeforeFirst()){
				rs.next();
				result = rs.getString("logging_chan");
			}
			
			ps.close();
			return result;
			
		} catch (SQLException e) {
			logger.error(e);
			return null;
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		
		
	}


}
