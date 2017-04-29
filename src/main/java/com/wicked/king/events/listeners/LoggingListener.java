package com.wicked.king.events.listeners;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wicked.king.KingBotv2Application;
import com.wicked.king.utils.Utils;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.ChannelCreateEvent;
import sx.blah.discord.handle.impl.events.guild.channel.ChannelDeleteEvent;
import sx.blah.discord.handle.impl.events.guild.channel.ChannelUpdateEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageDeleteEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessagePinEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageUpdateEvent;
import sx.blah.discord.handle.impl.events.guild.member.NicknameChangedEvent;
import sx.blah.discord.handle.impl.events.guild.member.UserBanEvent;
import sx.blah.discord.handle.impl.events.guild.member.UserJoinEvent;
import sx.blah.discord.handle.impl.events.guild.member.UserLeaveEvent;
import sx.blah.discord.handle.impl.events.guild.member.UserPardonEvent;
import sx.blah.discord.handle.impl.events.guild.member.UserRoleUpdateEvent;
import sx.blah.discord.handle.impl.events.guild.role.RoleCreateEvent;
import sx.blah.discord.handle.impl.events.guild.role.RoleDeleteEvent;
import sx.blah.discord.handle.impl.events.guild.role.RoleUpdateEvent;
import sx.blah.discord.handle.impl.events.user.PresenceUpdateEvent;
import sx.blah.discord.handle.impl.events.user.UserUpdateEvent;
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

    private static IGuild GUILD = null;

    private static final String CHANNEL_PREFIX = "**#";

    /**
     * Logger
     */
    private static final Logger logger = LogManager.getLogger(LoggingListener.class);

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
        GUILD = guild;
    }

    /**
     * Logs message when a new Channel is created
     * 
     * @param event
     */
    @EventSubscriber
    public void onChannelCreate(ChannelCreateEvent event) {
        Utils.writeMessageToChannel(
                "`" + timeFormat.format(new Date()) + "`" + " The channel " + CHANNEL_PREFIX
                + event.getChannel().getName() + "** was created.",
                event.getChannel().getGuild().getChannelByID(LOGGING_CHANNEL));

    }

    /**
     * Logs message when channel is deleted
     * 
     * @param event
     */
    @EventSubscriber
    public void onChannelDelete(ChannelDeleteEvent event) {
        Utils.writeMessageToChannel(
                "`" + timeFormat.format(new Date()) + "`" + " The channel " + CHANNEL_PREFIX
                + event.getChannel().getName() + "** was d.",
                event.getChannel().getGuild().getChannelByID(LOGGING_CHANNEL));
    }

    /**
     * Logs Message when a channel is updated
     * 
     * @param event
     */
    @EventSubscriber
    public void onChannelUpdate(ChannelUpdateEvent event) {
        if (!event.getOldChannel().getName().equals(event.getNewChannel().getName())) {
            Utils.writeMessageToChannel(
                    "`" + timeFormat.format(new Date()) + "`" + " The channel " + CHANNEL_PREFIX
                    + event.getOldChannel().getName() + "** was changed to " + CHANNEL_PREFIX
                    + event.getNewChannel().getName(),
                    event.getNewChannel().getGuild().getChannelByID(LOGGING_CHANNEL));

        } else if (event.getOldChannel().getTopic() == null && event.getNewChannel().getTopic() != null) {
            Utils.writeMessageToChannel(
                    "`" + timeFormat.format(new Date()) + "`" + " The channel " + CHANNEL_PREFIX
                    + event.getOldChannel().getName() + "** topic was changed to __*"
                    + event.getNewChannel().getTopic() + "*__",
                    event.getNewChannel().getGuild().getChannelByID(LOGGING_CHANNEL));

        } else if (!event.getOldChannel().getTopic().equals(event.getNewChannel().getTopic())) {

            Utils.writeMessageToChannel("`" + timeFormat.format(new Date()) + "`" + " The channel " + CHANNEL_PREFIX
                    + event.getOldChannel().getName() + "** topic was changed from __*"
                    + event.getOldChannel().getTopic() + "*__ to __*" + event.getNewChannel().getTopic() + "*__",
                    event.getNewChannel().getGuild().getChannelByID(LOGGING_CHANNEL));
        }
    }

    /**
     * Logs Message when a message is deleted
     * 
     * @param event
     */
    @EventSubscriber
    public void onMessageDelete(MessageDeleteEvent event) {
        Utils.writeMessageToChannel(
                "`" + timeFormat.format(new Date()) + "` **" + event.getMessage().getAuthor().getName()
                + "'s** message was deleted in *#" + event.getMessage().getChannel().getName() + ": "
                + event.getMessage(),
                event.getMessage().getChannel().getGuild().getChannelByID(LOGGING_CHANNEL));

    }

    /**
     * Logs message when a message is pinned
     * 
     * @param event
     */
    @EventSubscriber
    public void onMessagePin(MessagePinEvent event) {
        Utils.writeMessageToChannel(
                "`" + timeFormat.format(new Date()) + "` __**" + event.getMessage().getAuthor().getName()
                + "**__ Message: " + event.getMessage() + " was pinned in channel: __*#"
                + event.getChannel().getName() + "*__",
                event.getChannel().getGuild().getChannelByID(LOGGING_CHANNEL));
    }

    /**
     * Logs message when a message is updated
     * 
     * @param event
     */
    @EventSubscriber
    public void onMessageUpdate(MessageUpdateEvent event) {
        System.out.println("does this work?");
        if (event.getNewMessage().getAuthor().getClient() == KingBotv2Application.getClient()) {
            Utils.writeMessageToChannel(
                    "`" + timeFormat.format(new Date()) + "` __**" + event.getOldMessage().getAuthor().getName()
                    + "**__ Message was updated: \n **Before:** " + event.getOldMessage().getContent()
                    + " \n **After:** " + event.getNewMessage().getContent(),
                    event.getNewMessage().getChannel().getGuild().getChannelByID(LOGGING_CHANNEL));

        }

    }

    /**
     * Logs message when a role is created
     * 
     * @param event
     */
    @EventSubscriber
    public void onRoleCreate(RoleCreateEvent event) {
        Utils.writeMessageToChannel(
                "`" + timeFormat.format(new Date()) + "`" + " Role: " + event.getRole() + " has been created.",
                event.getGuild().getChannelByID(LOGGING_CHANNEL));
        // TODO fix
    }

    /**
     * Logs message when a role is deleted
     * 
     * @param event
     */
    @EventSubscriber
    public void onRoleDelete(RoleDeleteEvent event) {
        Utils.writeMessageToChannel(
                "`" + timeFormat.format(new Date()) + "`" + " Role: " + event.getRole() + " has been deleted.",
                event.getGuild().getChannelByID(LOGGING_CHANNEL));

    }

    /**
     * Logs message when a role is updated
     * 
     * @param event
     */
    @EventSubscriber
    public void onRoleUpdate(RoleUpdateEvent event) {
        // Utils.writeMessageToChannel("`" + timeFormat.format(new Date()) + "`"
        // +
        // " Role: " + event.getOldRole() + " has been updated to " +
        // event.getNewRole(),
        // event.getGuild().getChannelByID(botListener.getServerInfo(event.getGuild().getID()).getLoggingChannelId()));
        // TODO fix and dont spam. and remove mentions
    }

    /**
     * Logs message when a user is banned
     * 
     * @param event
     */
    @EventSubscriber
    public void onUserBan(UserBanEvent event) {
        Utils.writeMessageToChannel("`" + timeFormat.format(new Date()) + "`" + " The user: __**"
                + event.getUser().getName() + "**__ was banned from the server",
                event.getGuild().getChannelByID(LOGGING_CHANNEL));

    }

    /**
     * Logs message when a user joins the server
     * 
     * @param event
     */
    @EventSubscriber
    public void onUserJoin(UserJoinEvent event) {
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
        Utils.writeMessageToChannel("`" + timeFormat.format(new Date()) + "`" + " The user: __**"
                + event.getUser().getName() + "**__ has left the server",
                event.getGuild().getChannelByID(LOGGING_CHANNEL));

    }

    /**
     * Logs message when a user is unbanned
     * 
     * @param event
     */
    @EventSubscriber
    public void onUserPardon(UserPardonEvent event) {
        Utils.writeMessageToChannel("`" + timeFormat.format(new Date()) + "`" + " The user: __**"
                + event.getUser().getName() + "**__ was unbanned from the server",
                event.getGuild().getChannelByID(LOGGING_CHANNEL));

    }

    /**
     * Logs a message when a user has its role updated
     * 
     * @param event
     */
    @EventSubscriber
    public void onUserRoleUpdate(UserRoleUpdateEvent event) {
        logger.warn("User Role Update " + event.getUser());
    }

    /**
     * Logs message when a user updates there profile
     * 
     * @param event
     */
    @EventSubscriber
    public void onUserUpdate(UserUpdateEvent event) {
        logger.warn("User Update ");
        String message = "";
        boolean update = false;
        if (!event.getOldUser().getName().equals(event.getNewUser().getName())) {
            message = event.getOldUser().getName() + " has changed their name to: " + event.getNewUser().getName();

            update = true;

        } else if (!event.getOldUser().getAvatarURL().equals(event.getNewUser().getAvatarURL())) {
            message = event.getNewUser().getName() + " has changed avatars \n **Before:** "
                    + event.getOldUser().getAvatarURL() + "\n **After:** " + event.getNewUser().getAvatarURL();

            update = true;
        } else {
            return;
        }
        if (update) {
            Utils.writeMessageToChannel(message, GUILD.getChannelByID(LOGGING_CHANNEL));
        }
    }

    /**
     * Logs when somebodys nickname has been changed
     * 
     * @param event
     */
    @EventSubscriber
    public void onNicknameUpdate(NicknameChangedEvent event) {
        Utils.writeMessageToChannel(
                "`" + timeFormat.format(new Date()) + "` **" + event.getUser().getName()
                + "'s** nickname has changed from **" + event.getOldNickname().orElse("*blank*") + "** to **"
                + event.getNewNickname().orElse("*blank*") + "**",
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
                    GUILD.getChannelByID(231938880587169792l));
        }
    }

}
