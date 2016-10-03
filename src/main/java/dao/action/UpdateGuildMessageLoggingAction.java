package dao.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.GuildLoggingBean;

/**
 * Used to update the number of messages per hour in a server
 * @author King
 *
 */
public class UpdateGuildMessageLoggingAction {

	/**
	 * Logger
	 */
	private static final Logger logger = LogManager.getLogger(UpdateGuildMessageLoggingAction.class);

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
	public UpdateGuildMessageLoggingAction(Connection connection){
		conn = connection;
	}

	/**
	 * Creates the sql to run
	 */
	private void createSql(){
		StringBuilder sb = new StringBuilder();

		sb.append(" UPDATE ");
		sb.append(" T300_stats ");
		sb.append(" SET ");
		sb.append(" total_messages = ? ");
		sb.append(" WHERE ");
		sb.append(" T100_guildId = ?");
		sb.append(" AND hour = ?");

		sql = sb.toString();
	}


	/**
	 * 
	 * @param loggingBean
	 * @return
	 */
	public boolean execute(GuildLoggingBean loggingBean){
		return execute(loggingBean.getGuildId(), loggingBean.getHour(), loggingBean.getNumMessages());
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
			ps.setInt(1, numMessages);
			ps.setString(2, guildId);
			ps.setInt(3, hour);
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
