/**
 * The Main class. Logs the bot into discord and registers the listener classes.
 */
package login;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eventListeners.BotEventListener;
import eventListeners.HelpListener;
import eventListeners.LoggingListener;
import hidden.HiddenConstants;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.Status;
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
	
	private static final List<String> GAME_LIST = Arrays.asList("With the King", "Adulting", "outside", "with a friend", "breaking things", "nothing", "don't look at me");
	
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
		client.getDispatcher().registerListener(new HelpListener());
		
		final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
	    service.scheduleWithFixedDelay(new Runnable()
	      {
	        @Override
	        public void run()
	        {
	        	logger.warn("Inside timer");
	          Authorization.changeGame();
	        }
	      }, 0, 5, TimeUnit.MINUTES);

	}
	
	/**
	 * Changes the 'game' the bot is playing
	 */
	public static void changeGame(){
		Random rand = new Random();
		int num = rand.nextInt(GAME_LIST.size());
		client.changeStatus(Status.game(GAME_LIST.get(num)));
		
	}
	
	

}
