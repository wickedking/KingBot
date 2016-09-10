package dao.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bean.Keyword;

/**
 * Updates the xp of the user
 * @author King
 *
 */
public class UpdateXPAction {
	
	/**
	 * The sql to run
	 */
	private String sql;
	
	private String sql2;
	
	private String sql3;
	
	/**
	 * The connection
	 */
	private Connection conn;
	
	/**
	 * The constructor
	 * @param connection
	 */
	public UpdateXPAction(Connection connection){
		conn = connection;
	}
	
	/**
	 * Creates the sql to run
	 */
	private void createSql(){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" SELECT XP, update_time ");    
		sb.append(" FROM ");
		sb.append(" T102_rank ");
		sb.append(" WHERE ");
		sb.append(" userId = ? ");
		sb.append(" AND T100_guildId = ? ");
				
		sql = sb.toString();
	}
	
	/**
	 * creates second sql
	 */
	private void createSQL2(){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" UPDATE ");    
		sb.append(" T102_rank ");
		sb.append(" SET ");
		sb.append(" xp = ? ");
		sb.append(" WHERE ");
		sb.append(" userId = ? ");
		sb.append(" AND t100_guildId = ? ");
				
		sql2 = sb.toString();
	}
	
	/**
	 * creates third sql
	 */
	private void createSQL3(){
		StringBuilder sb = new StringBuilder();
		sb.append(" INSERT ");
		sb.append(" INTO T102_rank ");
		sb.append(" (T100_guildId, ");
		sb.append(" userId, ");
		sb.append(" xp ) ");
		sb.append(" VALUES ( ");
		sb.append(" ?, ");
		sb.append(" ?, ");
		sb.append(" ? ");
		sb.append(" )");
		
		
		sql3 = sb.toString();
		System.out.println(sql3);
	}
	
	/**
	 * Executes the sql and return success of sql
	 * @param keyword
	 * @return
	 */
	public boolean execute(String guildId, String userId, int xp){
		createSql();
		createSQL2();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, guildId);
			ResultSet rs = ps.executeQuery();
			if(!rs.isBeforeFirst()){
				createSQL3();
				PreparedStatement ps3 = conn.prepareStatement(sql3);
				ps3.setString(1, guildId);
				ps3.setString(2, userId);
				ps3.setInt(3, xp);
				ps3.execute();
			} else {
				rs.next();
				Calendar cal = Calendar.getInstance();
				Time time = rs.getTime("update_time");
				LocalTime lt = time.toLocalTime();
				LocalTime now = LocalTime.now();
				lt = lt.plusMinutes(1l);
				lt = lt.plusHours(1l);
				System.out.println(lt);
				System.out.println(now);
				if(now.isAfter(lt)){
					System.out.println("updated xp");
					int tableXp = rs.getInt("xp");
					PreparedStatement ps2 = conn.prepareStatement(sql2);
					ps2.setInt(1, tableXp + xp);
					ps2.setString(2, userId);
					ps2.setString(3, guildId);
					ps2.executeUpdate();
				}

			}
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
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
