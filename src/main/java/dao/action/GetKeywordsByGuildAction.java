package dao.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Keywords;

/**
 * Returns all the keywords for a guild
 * @author King
 *
 */
public class GetKeywordsByGuildAction {
	
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
	public GetKeywordsByGuildAction(Connection connection){
		conn = connection;
	}
	
	/**
	 * Creates the Sql needed
	 */
	private void createSQL(){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" SELECT * ");  
		sb.append(" FROM T101_key_words ");
		sb.append(" WHERE T100_guildId = ? ");
		
		sql = sb.toString();
	}

	/**
	 * Executes the sql and returns the result
	 * @param guildID
	 * @return
	 */
	public List<Keywords> execute(String guildID) {
		// TODO Auto-generated method stub
		createSQL();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, guildID);
			ResultSet rs = ps.executeQuery();
			List<Keywords> keywords = new ArrayList<Keywords>();
			
			while(rs.next()){
				Keywords keyword = new Keywords();
				keyword.setGuildId(rs.getString("T100_guildId"));
				keyword.setKeyword(rs.getString("keyword"));
				keyword.setAction1(rs.getString("action1"));
				keyword.setAction2(rs.getString("action2"));
				keyword.setMessage(rs.getString("message"));
				keywords.add(keyword);
			}
			
			return keywords;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<Keywords>();
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
