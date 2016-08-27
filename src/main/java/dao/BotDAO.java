package dao;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import bean.Keywords;
import dao.action.GetKeywordsByGuildAction;
import sx.blah.discord.handle.obj.IGuild;


//@Configuration
public class BotDAO {
	
	private Connection conn;
	
	
	//private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	//@Autowired
	public BotDAO(){//NamedParameterJdbcTemplate namedParameterJdbcTemplate){
		//this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
//		try {
//			try {
//				Class.forName("com.mysql.jdbc.Driver");
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			
		
	}
	
	private Connection getConnection(){
		try {
			conn = DriverManager.getConnection("");
			System.out.println("connection successful");
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void getUserLevel(){
		
	}
	
	public void getTopRankings(){
		
	}
	
	public List<Keywords> getKeywords(String guildID){
		GetKeywordsByGuildAction keywordAction = new GetKeywordsByGuildAction(getConnection());
		System.out.println("$$$$$$$$$$$$$$$$$$");
		System.out.println(guildID);
		return keywordAction.execute(guildID);
	}
	
	public void saveKeyword(){
		
	}
	
	public void deleteKeyword(){
		
	}
	
	public void updateUserXP(){
		
	}

}
