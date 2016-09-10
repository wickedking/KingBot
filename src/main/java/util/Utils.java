package util;

import java.util.List;
import java.util.Map;

import constants.BotConstants;
import details.ServerInfo;
import login.Authorization;
import sx.blah.discord.handle.impl.obj.PrivateChannel;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;
import sx.blah.discord.util.RequestBuffer;

/**
 * Utility class to provide common methods for posting messages and other methods
 * @author King
 *
 */
public class Utils {
	
	private static String guildId = "215588890382303234";
	
	/**
	 * Role for admin for the bot
	 */
	private static String botAdmin = "Bot Admin";

	/**
	 * Used to save user created Keywords
	 */
	public void saveKeywords() {

	}

	/**
	 * Logs message with the keywords for the server
	 * @return
	 */
	public Map<String, String> getKeywords(){

		return null;
	}

	/**
	 * Writes specified message to the specified channel
	 * @param message
	 * @param channel
	 */
	public static void WriteMessageToChannel(String message, IChannel channel){
		//edit message to remove the actual mentions so logging doesnt mention people
		RequestBuffer.request(() -> {
			MessageBuilder builder = new MessageBuilder(Authorization.client).withContent(message);
			builder.withChannel(channel);
			try {
				builder.build();
			} catch (DiscordException | MissingPermissionsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

	}
	
	/**
	 * Will private message the user with the message
	 * @param user
	 * @param message
	 */
	public static void PrivateMessageUser(IUser user, String message){
		PrivateChannel dm = new PrivateChannel(Authorization.client, user, message);
		try {
			dm.sendMessage("blargh");
		} catch (RateLimitException | MissingPermissionsException | DiscordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Will determine if the user has the admin role
	 * @param roles
	 * @return
	 */
	public static boolean isBotAdmin(List<IRole> roles){
		for(IRole role : roles){
			if(role.getName().equals(botAdmin)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Initialize database, return mapping info
	 * @return
	 */
	public static void  StartupDBBot(){
		
		
	}
	
	/**
	 * validation for keywords, action word 1
	 * @param action1
	 * @return
	 */
	public static boolean validateAction1(String action1){
		String[] actions = {BotConstants.KEEP, BotConstants.DELETE};
		for(String action : actions){
			if(action1.trim().equals(action)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * validation for keywords, action word 2
	 * @param action2
	 * @return
	 */
	public static boolean validateAction2(String action2){
		String[] actions = {BotConstants.STANDALONE, BotConstants.INCLUDED};
		for(String action : actions){
			if(action2.trim().equals(action)){
				return true;
			}
		}
		return false;
	}
}
