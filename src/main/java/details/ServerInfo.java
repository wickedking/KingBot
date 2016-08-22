package details;
import java.util.Map;

/**
 * Work in progress class
 * @author King
 *
 */
public class ServerInfo {

	private String guildId;
	private String loggingChannelId;
	private Map<String, String> keywords;
	
	
	public String getGuildId() {
		return guildId;
	}
	
	public void setGuildId(String guildId) {
		this.guildId = guildId;
	}
	
	public String getLoggingChannelId() {
		return loggingChannelId;
	}
	
	public void setLoggingChannelId(String loggingChannelId) {
		this.loggingChannelId = loggingChannelId;
	}
	
	public Map<String, String> getKeywords() {
		return keywords;
	}
	
	public void setKeywords(Map<String, String> keywords) {
		this.keywords = keywords;
	}
	
}
