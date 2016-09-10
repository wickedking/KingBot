/**
 * The Main class. Logs the bot into discord and regristers the listener classes.
 */
package login;

import eventListeners.BotEventListener;
import eventListeners.LoggingListener;
import hidden.HiddenConstants;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;

/**
 * 
 * @author WickedKing
 *
 */
//@ComponentScan
//@SpringBootApplication
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
	 * Da Main method yo
	 * @param args
	 * @throws DiscordException
	 */
	public static void main(String[] args) throws DiscordException {
		System.out.println("Hello World");
		//SpringApplication.run(Authorization.class, args);
		client = new ClientBuilder().withToken(HiddenConstants.BOTTOKEN).login();
		BotEventListener listener = new BotEventListener();
		client.getDispatcher().registerListener(listener);
		client.getDispatcher().registerListener(new LoggingListener(listener));
	
	}

}
