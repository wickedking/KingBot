package com.wicked.king;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.wicked.king.db.DBAccessorPerson;
import com.wicked.king.db.DBAccessorUtils;
import com.wicked.king.events.listeners.BotEventListener;
import com.wicked.king.events.listeners.HelpListener;
import com.wicked.king.events.listeners.LoggingListener;
import com.wicked.king.events.listeners.MessageParseListener;
import com.wicked.king.hidden.HiddenConstants;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;

@SpringBootApplication
@EnableAutoConfiguration
@EnableMongoRepositories(basePackageClasses=DBAccessorUtils.class)
@ComponentScan({"com.wicked.king"})
public class KingBotv2Application implements CommandLineRunner{
	
private static final Logger logger = LogManager.getLogger(KingBotv2Application.class);
	
	/**
	 * A static reference to the DiscordClient Object
	 */
	private static IDiscordClient client = new ClientBuilder().withToken(HiddenConstants.BOTTOKEN).login();
	
	@Autowired
	private DBAccessorUtils repository;
	
	@Autowired
	private DBAccessorPerson repository2;
	
	/**
	 * Hardcoded Guild Id. Used mainly for testing.
	 */
	private static final String GUILD_ID = "129063193493372929";

	public static void main(String[] args) {
		SpringApplication.run(KingBotv2Application.class, args);
		
	}
	
	@Override
	public void run(String... args) {
		
		logger.warn("Hello World");
		BotEventListener listener = new BotEventListener(repository);
		client.getDispatcher().registerListener(listener);		
		client.getDispatcher().registerListener(new LoggingListener(client.getGuildByID(Long.parseUnsignedLong(GUILD_ID))));
		client.getDispatcher().registerListener(new HelpListener());
		client.getDispatcher().registerListener(new MessageParseListener(repository2));
		listener.sendRepo();
	}
	
	public static IDiscordClient getClient() {
		return client;
	}
	
	
}
