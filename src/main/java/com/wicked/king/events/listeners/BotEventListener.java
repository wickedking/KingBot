package com.wicked.king.events.listeners;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wicked.king.KingBotv2Application;
import com.wicked.king.constants.BotConstants;
import com.wicked.king.db.DBAccessorUtils;
import com.wicked.king.events.AboutEvent;
import com.wicked.king.events.AdminCommandsEvent;
import com.wicked.king.events.AdviceEvent;
import com.wicked.king.events.AwwEvent;
import com.wicked.king.events.BanEvent;
import com.wicked.king.events.CommandsEvent;
import com.wicked.king.events.EightBallEvent;
import com.wicked.king.events.GetServerInfoEvent;
import com.wicked.king.events.InsultEvent;
import com.wicked.king.events.JokeEvent;
import com.wicked.king.events.KickEvent;
import com.wicked.king.events.LennyEvent;
import com.wicked.king.events.MiddleFingerEvent;
import com.wicked.king.events.PruneEvent;
import com.wicked.king.events.ShrugEvent;
import com.wicked.king.events.TableFlipEvent;
import com.wicked.king.events.TimeoutEvent;
import com.wicked.king.events.UnTableFlipEvent;
import com.wicked.king.utils.Utils;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IPrivateChannel;
import sx.blah.discord.handle.obj.IRegion;
import sx.blah.discord.handle.obj.IUser;

import sx.blah.discord.handle.obj.VerificationLevel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageHistory;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

/**
 * Holds all the event listeners in the methods.
 * 
 * @author WickedKing
 *
 */
@Component
public class BotEventListener {

    @Autowired
    private DBAccessorUtils repository;

    /**
     * List of games for the bot to 'play'
     */
    private static final List<String> GAME_LIST = Arrays.asList("With the King", "Adulting", "Outside", "with a friend",
            "Breaking things", "Nothing", "Don't look at me");

    private static final Logger logger = LogManager.getLogger(BotEventListener.class);

    public BotEventListener(DBAccessorUtils repo) {
        repository = repo;
    }

    /**
     * Writes the shrug ascii out to the channel
     * 
     * @param event
     */
    @EventSubscriber
    public void onShrug(ShrugEvent event) {
        Utils.writeMessageToChannel(BotConstants.SHRUG, event.getMessage().getChannel());
    }

    /**
     * Writes the shrug ascii out to the channel
     * 
     * @param event
     */
    @EventSubscriber
    public void onTableFlip(TableFlipEvent event) {
        Utils.writeMessageToChannel(BotConstants.TABLE_FLIP, event.getMessage().getChannel());
    }

    /**
     * Writes the shrug ascii out to the channel
     * 
     * @param event
     */
    @EventSubscriber
    public void onUnTableFlip(UnTableFlipEvent event) {
        Utils.writeMessageToChannel(BotConstants.UN_TABLE_FLIP, event.getMessage().getChannel());
    }

    /**
     * used to write lenny face
     * 
     * @param event
     */
    @EventSubscriber
    public void onLenny(LennyEvent event) {
        Utils.writeMessageToChannel("( ͡° ͜ʖ ͡°)", event.getMessage().getChannel());
    }

    /**
     * Used to write middle finger ascii to channel
     * 
     * @param event
     */
    @EventSubscriber
    public void onMiddleFinger(MiddleFingerEvent event) {
        Utils.writeMessageToChannel("╭∩╮(Ο_Ο)╭∩╮", event.getMessage().getChannel());
    }

    /**
     * Bans the user mentioned
     * 
     * @param event
     */
    @EventSubscriber
    public void onBan(BanEvent event) {
        boolean isAdmin = Utils.isBotAdmin(event.getMessage().getAuthor(), event.getMessage().getGuild().getOwner(),
                event.getMessage().getAuthor().getRolesForGuild(event.getMessage().getGuild()));
        if (isAdmin) {
            IGuild guild = event.getClient().getGuilds().get(0);
            try {
                KingBotv2Application.getClient().getGuildByID(Long.parseUnsignedLong(guild.getStringID()))
                .banUser(event.getMessage().getMentions().get(0));
            } catch (RateLimitException | MissingPermissionsException | DiscordException e) {
                logger.error(e);
            }
        } else {
            Utils.writeMessageToChannel(BotConstants.NOT_AUTHORIZED, event.getMessage().getChannel());
        }
    }

    /**
     * Kicks the user mentioned
     * 
     * @param event
     */
    @EventSubscriber
    public void onKick(KickEvent event) {

        boolean isAdmin = Utils.isBotAdmin(event.getMessage().getAuthor(), event.getMessage().getGuild().getOwner(),
                event.getMessage().getAuthor().getRolesForGuild(event.getMessage().getGuild()));
        if (isAdmin) {
            try {
                KingBotv2Application.getClient().getGuilds().get(0).kickUser(event.getMessage().getMentions().get(0));
            } catch (RateLimitException | MissingPermissionsException | DiscordException e) {
                logger.error(e);
            }
        } else {
            Utils.writeMessageToChannel(BotConstants.NOT_AUTHORIZED, event.getMessage().getChannel());
        }

    }

    /**
     * Handles a timeout for the user mentioned TODO
     * 
     * @param event
     */
    @EventSubscriber
    public void onTimeout(TimeoutEvent event) {
        boolean isAdmin = Utils.isBotAdmin(event.getMessage().getAuthor(), event.getMessage().getGuild().getOwner(),
                event.getMessage().getAuthor().getRolesForGuild(event.getMessage().getGuild()));
        if (isAdmin) {
            Utils.writeMessageToChannel(BotConstants.UNFINISHED, event.getMessage().getChannel());
        } else {
            Utils.writeMessageToChannel(BotConstants.NOT_AUTHORIZED, event.getMessage().getChannel());
        }

    }

    /**
     * Deletes the requested number of messages from channel requested from
     * 
     * @param event
     */
    @EventSubscriber
    public void onPrune(PruneEvent event) {
        boolean isAdmin = Utils.isBotAdmin(event.getMessage().getAuthor(), event.getMessage().getGuild().getOwner(),
                event.getMessage().getAuthor().getRolesForGuild(event.getMessage().getGuild()));
        if (isAdmin) {
            String totalMessage = event.getMessage().getContent();
            int index = totalMessage.indexOf(' ');
            int numMessages = Integer.parseInt(totalMessage.substring(index).trim());
            MessageHistory list = event.getMessage().getChannel().getMessageHistory(numMessages);
            list.bulkDelete();

        } else { 
            Utils.writeMessageToChannel(BotConstants.NOT_AUTHORIZED, event.getMessage().getChannel());
        }

    }

    /**
     * Used to provide basic information about the bot.
     * 
     * @param event
     */
    @EventSubscriber
    public void onAbout(AboutEvent event) {
        Utils.writeMessageToChannel(BotConstants.ABOUT_MESSAGE, event.getMessage().getChannel());
    }

    /**
     * Provides an 8ball answer to a question
     * 
     * @param event
     */
    @EventSubscriber
    public void onEightBall(EightBallEvent event) {
        List<String> eightBall = Utils.getEightBallAnswers();
        Random random = new Random();
        int choice = random.nextInt(eightBall.size());
        Utils.writeMessageToChannel(event.getMessage().getAuthor().mention() + " " + eightBall.get(choice),
                event.getMessage().getChannel());
    }

    /**
     * Provides a joke
     * 
     * @param event
     */
    @EventSubscriber
    public void onJoke(JokeEvent event) {
        String joke = Utils.getJoke();
        Utils.writeMessageToChannel(joke, event.getMessage().getChannel());
    }

    /**
     * Posts usually (not) helpful advice
     * 
     * @param event
     */
    @EventSubscriber
    public void onAdvice(AdviceEvent event) {
        Utils.writeMessageToChannel(Utils.getAdvice(), event.getMessage().getChannel());
    }

    /**
     * Posts a somewhat nice insult
     * 
     * @param event
     */
    @EventSubscriber
    public void onInsult(InsultEvent event) {
        Utils.writeMessageToChannel(Utils.getInsult(), event.getMessage().getChannel());
    }

    /**
     * Posts the commands in the channel requested
     * 
     * @param event
     */
    @EventSubscriber
    public void onCommands(CommandsEvent event) {
        try {
            IPrivateChannel channel = KingBotv2Application.getClient()
                    .getOrCreatePMChannel(event.getMessage().getAuthor());
            Utils.writeMessageToChannel(BotConstants.HELP_MESSAGE, channel);
            Utils.writeMessageToChannel(
                    event.getMessage().getAuthor().mention() + " The commands have been messaged to you",
                    event.getMessage().getChannel());

        } catch (DiscordException | RateLimitException e) {
            logger.error(e);
        }

    }

    /**
     * Private message the user with the String of admin commands
     * 
     * @param event
     */
    @EventSubscriber
    public void onAdminCommands(AdminCommandsEvent event) {
        try {
            IPrivateChannel channel = KingBotv2Application.getClient()
                    .getOrCreatePMChannel(event.getMessage().getAuthor());
            Utils.writeMessageToChannel(BotConstants.ADMIN_HELP_MESSAGE, channel);
            Utils.writeMessageToChannel(
                    event.getMessage().getAuthor().mention() + " The commands have been messaged to you",
                    event.getMessage().getChannel());

        } catch (DiscordException | RateLimitException e) {
            logger.error(e);
        }
    }

    /**
     * Get basic info about the server
     * 
     * @param event
     */
    @EventSubscriber
    public void onGetServerInfo(GetServerInfoEvent event) {
        IGuild guild = event.getMessage().getGuild();
        LocalDateTime creationTime = guild.getCreationDate();
        String icon = guild.getIconURL();
        String guildName = guild.getName();
        IUser owner = guild.getOwner();
        IRegion region = guild.getRegion();
        int totalUsers = guild.getTotalMemberCount();
        VerificationLevel verificationLevel = guild.getVerificationLevel();
        int voiceChannelNumber = guild.getVoiceChannels().size();
        int numberChannels = guild.getChannels().size();
        int numberWebhooks = guild.getWebhooks().size();
        int emojis = guild.getEmojis().size();

        Utils.writeMessageToChannel(
                "```Creation date: " + creationTime + "\n Icon: " + icon + "\n GuildName: " + guildName + "\n Owner: "
                        + owner.getName() + "\n Region: " + region + "\n Total Users: " + totalUsers
                        + "\n Verification level: " + verificationLevel + "\n WebHooks: " + numberWebhooks
                        + "\n Number of Text channels: " + numberChannels + "\n Number voice channels: "
                        + voiceChannelNumber + "\n Number of custom Emojis " + emojis + "```",
                        event.getMessage().getChannel());
    }

    @EventSubscriber
    public void changeGameEvent(ReadyEvent event) {
        final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                logger.warn("Inside timer");
                BotEventListener.changeGame(event);
            }
        }, 0, 30, TimeUnit.MINUTES);
    }
    
    @EventSubscriber
    public void onAwwEvent(AwwEvent event){
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.reddit.com/r/aww/").get();
        } catch (IOException e) {
            logger.warn(e);
        }

        Elements elements = doc.getElementsByClass("sitetable");

        String html = doc.html();
        System.out.println(elements.size());
        int index = html.indexOf("https://i.redditmedia.com/");
        html = html.substring(index);
        int endIndex = html.indexOf(".jpg");
        String link = html.substring(0, endIndex + 4);
        System.out.println("This is the link: " +  link);



    }

    /**
     * Changes the 'game' the bot is playing
     */
    public static void changeGame(ReadyEvent event) {
        Random rand = new Random();
        int num = rand.nextInt(GAME_LIST.size());
        event.getClient().changePlayingText(GAME_LIST.get(num));

    }

    public void sendRepo() {
        Utils.setRepo(repository);

    }

}
