package com.wicked.king.events.listeners;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.wicked.king.KingBotv2Application;
import com.wicked.king.utils.Utils;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageDeleteEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageUpdateEvent;
import sx.blah.discord.handle.impl.events.guild.member.UserJoinEvent;
import sx.blah.discord.handle.impl.events.guild.member.UserLeaveEvent;
import sx.blah.discord.handle.impl.events.user.PresenceUpdateEvent;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.StatusType;

/**
 * The event listener for logging events.
 * 
 * @author King
 *
 */
public class LoggingListener {

    private static final long LOGGING_CHANNEL = 204639036189442048l;

    private static IGuild Guild = null;

    /**
     * Static reference to time formatter
     */
    private SimpleDateFormat timeFormat = new SimpleDateFormat("KK:mm:ss z");

    /**
     * Default constructor
     * 
     * @param listener
     */
    public LoggingListener(IGuild guild) {
        Guild = guild;
    }
    
    private String getLoggingChannel(IGuild guild){
        return Utils.getServerInfo(guild.getStringID()).getLoggingChannel();
    }
    
    private boolean isLoggingEnabled(IGuild guild){
        return Utils.getServerInfo(guild.getStringID()).isDoLogging();
    }
    

    /**
     * Logs Message when a message is deleted
     * 
     * @param event
     */
    @EventSubscriber
    public void onMessageDelete(MessageDeleteEvent event) {
        String channelId = getLoggingChannel(event.getGuild());
        if(null == channelId || !isLoggingEnabled(event.getGuild())){
            return;
        }
        Utils.writeMessageToChannel(
                "`" + timeFormat.format(new Date()) + "` **" + event.getMessage().getAuthor().getName()
                + "'s** message was deleted in *#" + event.getMessage().getChannel().getName() + ": "
                + event.getMessage(),
                event.getMessage().getChannel().getGuild().getChannelByID(Long.parseLong(channelId)));

    }


    /**
     * Logs message when a message is updated
     * 
     * @param event
     */
    @EventSubscriber
    public void onMessageUpdate(MessageUpdateEvent event) {
        String channelId = getLoggingChannel(event.getGuild());
        if(null == channelId || !isLoggingEnabled(event.getGuild())){
            return;
        }
        if (event.getNewMessage().getAuthor().getClient() == KingBotv2Application.getClient()) {
            Utils.writeMessageToChannel(
                    "`" + timeFormat.format(new Date()) + "` __**" + event.getOldMessage().getAuthor().getName()
                    + "**__ Message was updated: \n **Before:** " + event.getOldMessage().getContent()
                    + " \n **After:** " + event.getNewMessage().getContent(),
                    event.getNewMessage().getChannel().getGuild().getChannelByID(LOGGING_CHANNEL));

        }

    }


    /**
     * Logs message when a user joins the server
     * 
     * @param event
     */
    @EventSubscriber
    public void onUserJoin(UserJoinEvent event) {
        String channelId = getLoggingChannel(event.getGuild());
        if(null == channelId || !isLoggingEnabled(event.getGuild())){
            return;
        }
        Utils.writeMessageToChannel("`" + timeFormat.format(new Date()) + "`" + " The user: __**"
                + event.getUser().getName() + "**__ has joined the server",
                event.getGuild().getChannelByID(LOGGING_CHANNEL));

    }

    /**
     * Logs message when a user leaves the server
     * 
     * @param event
     */
    @EventSubscriber
    public void onUserLeave(UserLeaveEvent event) {
        String channelId = getLoggingChannel(event.getGuild());
        if(null == channelId || !isLoggingEnabled(event.getGuild())){
            return;
        }
        Utils.writeMessageToChannel("`" + timeFormat.format(new Date()) + "`" + " The user: __**"
                + event.getUser().getName() + "**__ has left the server",
                event.getGuild().getChannelByID(LOGGING_CHANNEL));

    }

    /**
     * Posts when a certain person goes live
     * 
     * Will update in future to use DB and dynamic lists TODO
     * 
     * @param event
     */
    @EventSubscriber
    public void onPresenceChangeEvent(PresenceUpdateEvent event) {
        
        if (event.getNewPresence().getStatus() == StatusType.STREAMING
                && "Element Box".equals(event.getUser().getName())) {
            Utils.writeMessageToChannel(
                    "@everyone EB is now streaming on twitch. Go to https://www.twitch.tv/element_box and hang out!!",
                    Guild.getChannelByID(231938880587169792l));
        }
    }

}
