/**
 * The Main class. Logs the bot into discord and regristers the listener classes.
 */
package login;

import eventListeners.BotEventListener;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;

/**
 * 
 * @author WickedKing
 *
 */
public class Authorization {

	/**
	 * A static reference to the DiscordClient Object
	 */
	public static IDiscordClient client;
	
	/**
	 * A status for when the bot is logged in and ready
	 */
	public static boolean readyStatus = false;
	
	/**
	 * 
	 * @param args
	 * @throws DiscordException
	 */
	public static void main(String[] args) throws DiscordException {
		System.out.println("Hello World");
		client = new ClientBuilder().withToken("").login();
		client.getDispatcher().registerListener(new BotEventListener());
		
	}

}
