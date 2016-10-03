package dao.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.GuildLoggingBean;

/**
 * Grabs the number of messages used in the server 
 * 
 * @author King
 *
 */
public class GetGuildMessageLoggingAction {
	
	
	private static final Logger logger = LogManager.getLogger(GetGuildMessageLoggingAction.class);
	
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
	public GetGuildMessageLoggingAction(Connection connection){
		conn = connection;
	}
	
	/**
	 * Creates the sql to run
	 */
	private void createSql(){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" SELECT ");
		sb.append(" T100_guildId, ");
		sb.append(" hour, ");
		sb.append(" total_messages,");
		sb.append(" FROM ");
		sb.append(" T300_stats ");
		sb.append(" WHERE");
		sb.append(" T100_guildId = ? ");
		sb.append(" AND hour = ?");

		sql = sb.toString();
	}
	
	
	/**
	 * 
	 * @param guildId
	 * @param hour

	 * @return
	 */
	public GuildLoggingBean execute(String guildId, int hour){
		createSql();
		logger.warn(sql);
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, guildId);
			ps.setInt(2, hour);
			ResultSet rs  = ps.executeQuery();
			GuildLoggingBean loggingBean = new GuildLoggingBean();
			if(rs.next()){
				loggingBean.setGuildId(rs.getString("T100_guildId"));
				loggingBean.setHour(hour);
				loggingBean.setNumMessages(rs.getInt("total_messages"));
			} else {
				loggingBean = null;
			}
			
			ps.close();
			return loggingBean;
			
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
