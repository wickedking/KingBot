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
		client = new ClientBuilder().withToken("MjE1NjAzMjM5NzIxMjM4NTI4.CpZ9_Q.SEpxJMXqnmqHSuUXuQkEPwdZVwY").login();
		client.getDispatcher().registerListener(new ReadyEventTest());
		
	}

}
