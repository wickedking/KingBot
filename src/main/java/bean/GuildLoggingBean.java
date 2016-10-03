package bean;

/**
 * 
 * @author King
 *
 */
public class GuildLoggingBean {

	private String guildId;
	private int hour;
	private int numMessages;

	/**
	 * 
	 * @return
	 */
	public String getGuildId() {
		return guildId;
	}

	/**
	 * 
	 * @param guildId
	 */
	public void setGuildId(String guildId) {
		this.guildId = guildId;
	}

	/**
	 * 
	 * @return
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * 
	 * @param hour
	 */
	public void setHour(int hour) {
		this.hour = hour;
	}

	/**
	 * 
	 * @return
	 */
	public int getNumMessages() {
		return numMessages;
	}

	/**
	 * 
	 * @param numMessages
	 */
	public void setNumMessages(int numMessages) {
		this.numMessages = numMessages;
	}


}
