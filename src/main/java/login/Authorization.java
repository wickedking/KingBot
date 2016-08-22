/**
 * The Main class. Logs the bot into discord and regristers the listener classes.
 */
package login;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import eventListeners.BotEventListener;
import eventListeners.LoggingListener;
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
	 * Da Main method yo
	 * @param args
	 * @throws DiscordException
	 */
	public static void main(String[] args) throws DiscordException {
		System.out.println("Hello World");
		client = new ClientBuilder().withToken("MjE1NjAzMjM5NzIxMjM4NTI4.Cpf6lg.16OAQyifDekwKkOrVXPujqjynA4").login();
		client.getDispatcher().registerListener(new BotEventListener());
		client.getDispatcher().registerListener(new LoggingListener());
	
	}

}
