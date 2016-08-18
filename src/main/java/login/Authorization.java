package login;

import eventListeners.ReadyEventTest;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;

public class Authorization {

	public static IDiscordClient client;
	public static boolean readyStatus = false;
	
	public static void main(String[] args) throws DiscordException {
		System.out.println("Hello World");
		client = new ClientBuilder().withToken("").login();
		client.getDispatcher().registerListener(new ReadyEventTest());
		
	}

}
