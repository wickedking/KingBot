package dao.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Deletes the specified keyword from the database
 * @author King
 *
 */
public class GetXPLevelForUserAction {
	
	private static final Logger logger = LogManager.getLogger(GetXPLevelForUserAction.class);
	
	/**
	 * The Sql to run
	 */
	private String sql;
	
	/**
	 * The connection
	 */
	private Connection conn;
	
	/**
	 * Default constructor
	 * @param connection
	 */
	public GetXPLevelForUserAction(Connection connection){
		conn = connection;
	}
	
	/**
	 * creates the sql
	 */
	private void createSql(){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" SELECT ");
		sb.append(" T202.Level as level ");
		sb.append(" FROM T202_levels T202 ");
		sb.append(" WHERE ");
		sb.append(" T202.xp_total > ? ");
		sb.append(" ORDER BY level ");
		sb.append(" limit 1 ");
		
		sql = sb.toString();
	}
	
	/**
	 * Execute the sql and returns the server infos
	 * @return
	 */
	public int execute(int xp){
		createSql();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, xp);
			ResultSet rs = ps.executeQuery();
			int level = 0;
			System.out.println("before resultSet");
			rs.next();
			level = rs.getInt("level");
			System.out.println("The level is: " + level);
			ps.close();
			return level;
			
		} catch (SQLException e) {
			logger.error(e);
			return -1;
		} finally{
			try {
				if(conn != null && !conn.isClosed()){
					conn.close();
				}
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		
		
	}


}
