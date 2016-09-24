package details;
import java.util.ArrayList;
import java.util.List;
import bean.Keyword;
import login.Authorization;
import sx.blah.discord.util.audio.AudioPlayer;

/**
 * Holds Basic data for each server the bot is in
 * @author King
 *
 */
public class ServerInfo {
	
	private AudioPlayer player;

	/**
	 * The Guild id
	 */
	private String guildId;
	
	/**
	 * The Logging channel for the guild
	 */
	private String loggingChannelId;
	
	/**
	 * The list of keywords
	 */
	private List<Keyword> keywords;
	
	/**
	 * get the Guild id
	 * @return
	 */
	public String getGuildId() {
		return guildId;
	}
	
	/**
	 * set the guild id
	 * @param guildId
	 */
	public void setGuildId(String guildId) {
		this.guildId = guildId;
	}
	
	/**
	 * get the logging channel id
	 * @return
	 */
	public String getLoggingChannelId() {
		return loggingChannelId;
	}
	
	/**
	 * set the logging channel id
	 * @param loggingChannelId
	 */
	public void setLoggingChannelId(String loggingChannelId) {
		this.loggingChannelId = loggingChannelId;
	}
	
	/**
	 * get the keywords
	 * @return
	 */
	public List<Keyword> getKeywords() {
		if(keywords == null){
			return new ArrayList<>();
		} 
		return keywords;
		
	}
	
	/**
	 * set the keywords
	 * @param keywords
	 */
	public void setKeywords(List<Keyword> keywords) {
		this.keywords = keywords;
	}

	/**
	 * returns an instance of the audio player
	 * @return
	 */
	public AudioPlayer getPlayer() {
		if(player == null){
			player = AudioPlayer.getAudioPlayerForGuild(Authorization.client.getGuildByID(guildId));
		}		
		return player;
	}

	/**
	 * sets the audio player
	 * @param player
	 */
	public void setPlayer(AudioPlayer player) {
		this.player = player;
	}
	
}
