package dao.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.Keyword;

/**
 * Returns all the keywords for a guild
 * @author King
 *
 */
public class GetKeywordsByGuildAction {
	
	private static final Logger logger = LogManager.getLogger(GetKeywordsByGuildAction.class);
	
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
	public List<Keyword> execute(String guildID) {
		createSQL();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, guildID);
			ResultSet rs = ps.executeQuery();
			List<Keyword> keywords = new ArrayList<>();
			
			while(rs.next()){
				Keyword keyword = new Keyword();
				keyword.setGuildId(rs.getString("T100_guildId"));
				keyword.setKeyword(rs.getString("keyword"));
				keyword.setAction1(rs.getString("action1"));
				keyword.setAction2(rs.getString("action2"));
				keyword.setMessage(rs.getString("message"));
				keywords.add(keyword);
			}
			
			ps.close();
			return keywords;
		} catch (SQLException e) {
			logger.error(e);
			return new ArrayList<>();
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		
		
	}
	
	

}
