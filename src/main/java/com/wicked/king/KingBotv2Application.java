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
import org.springframework.scheduling.annotation.EnableScheduling;

import com.wicked.king.db.DBAccessorPerson;
import com.wicked.king.db.DBAccessorServerInfo;
import com.wicked.king.db.DBAccessorStats;
import com.wicked.king.db.DBAccessorUtils;
import com.wicked.king.events.listeners.BotEventListener;
import com.wicked.king.events.listeners.HelpListener;
import com.wicked.king.events.listeners.LoggingListener;
import com.wicked.king.events.listeners.MessageParseListener;
import com.wicked.king.events.listeners.StatsListener;
import com.wicked.king.hidden.HiddenConstants;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;

/**
 * Main Class for starting the Bot. Enables Framework and registers listeners and needed classes for runtime.
 * 
 * @author King
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableMongoRepositories(basePackageClasses=DBAccessorUtils.class)
@ComponentScan({"com.wicked.king"})
@EnableScheduling
public class KingBotv2Application implements CommandLineRunner{

    /**
     * Logger implementation
     */
    private static final Logger logger = LogManager.getLogger(KingBotv2Application.class);

    /**
     * A static reference to the DiscordClient Object
     */
    private static IDiscordClient client = new ClientBuilder().withToken(HiddenConstants.BOTTOKEN).login();

    /**
     * Proxy implementation of DB for Utilitys
     */
    @Autowired
    private DBAccessorUtils repository;

    /**
     * Proxy implementation of DB for People info.
     */
    @Autowired
    private DBAccessorPerson repository2;

    /**
     * Proxy implementation of DB for Server Info.
     */
    @Autowired
    private DBAccessorServerInfo repository3;

    /**
     * Proxy implementation for saving stats.
     */
    @Autowired 
    private DBAccessorStats repo4;

    /**
     * Hardcoded Guild Id. Used mainly for testing.
     */
    private static final String GUILD_ID = "129063193493372929";

    /**
     * Main Method. Starts everything
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(KingBotv2Application.class, args);

    }

    /**
     * Starts the application, logs in, and registers all the listeners
     */
    @Override
    public void run(String... args) {

        logger.warn("Hello World");
        BotEventListener listener = new BotEventListener(repository, repository3);
        client.getDispatcher().registerListener(listener);		
        client.getDispatcher().registerListener(new LoggingListener(client.getGuildByID(Long.parseUnsignedLong(GUILD_ID))));
        client.getDispatcher().registerListener(new HelpListener());
        client.getDispatcher().registerListener(new MessageParseListener(repository2));
        client.getDispatcher().registerListener(new StatsListener(repo4));
        listener.sendRepo();
    }

    /**
     * Returns a reference to the Client of the bot itself.
     * 
     * @return IDiscordClient A reference to the client
     */
    public static IDiscordClient getClient() {
        return client;
    }


}
