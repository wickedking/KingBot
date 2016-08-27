package bean;

/**
 * Bean to hold the data for a keyword
 * @author King
 *
 */
public class Keywords {
	
	private String guildId;
	private String keyword;
	private String action1;
	private String action2;
	private String message;
	
	/**
	 * Returns the guildId
	 * @return
	 */
	public String getGuildId() {
		return guildId;
	}
	
	/**
	 * Sets the guildId
	 * @param guildId
	 */
	public void setGuildId(String guildId) {
		this.guildId = guildId;
	}
	
	/**
	 * Returns the actual keyword
	 * @return
	 */
	public String getKeyword() {
		return keyword;
	}
	
	/**
	 * sets the actual keyword
	 * @param keyword
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	/**
	 * returns the first action
	 * @return
	 */
	public String getAction1() {
		return action1;
	}
	
	/**
	 * sets the first action
	 * @param action1
	 */
	public void setAction1(String action1) {
		this.action1 = action1;
	}
	
	/**
	 * Returns the second action
	 * @return
	 */
	public String getAction2() {
		return action2;
	}
	
	/**
	 * Sets the second action
	 * @param action2
	 */
	public void setAction2(String action2) {
		this.action2 = action2;
	}
	
	/**
	 * Returns the message
	 * @return
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Sets the actual message
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString(){
		return keyword + " " + action1 + " " + action2 + " " + message;
	}
	

}
