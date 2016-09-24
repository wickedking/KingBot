/**
 * The Main class. Logs the bot into discord and regristers the listener classes.
 */
package login;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

	private static final Logger logger = LogManager.getLogger(Authorization.class);
	
	/**
	 * A static reference to the DiscordClient Object
	 */
	public static IDiscordClient client;	
	
	private Authorization(){
		//empty constructor
	}
	
	/**
	 * Da Main method yo
	 * @param args
	 * @throws DiscordException
	 */
	public static void main(String[] args) throws DiscordException {
		logger.warn("Hello World");
		client = new ClientBuilder().withToken(HiddenConstants.BOTTOKEN).login();
		BotEventListener listener = new BotEventListener();
		client.getDispatcher().registerListener(listener);
		client.getDispatcher().registerListener(new LoggingListener(listener));
	
	}
	
	

}
