package eventListeners;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import details.ServerInfo;
import login.Authorization;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ChannelCreateEvent;
import sx.blah.discord.handle.impl.events.ChannelDeleteEvent;
import sx.blah.discord.handle.impl.events.ChannelUpdateEvent;
import sx.blah.discord.handle.impl.events.MessageDeleteEvent;
import sx.blah.discord.handle.impl.events.MessagePinEvent;
import sx.blah.discord.handle.impl.events.MessageUpdateEvent;
import sx.blah.discord.handle.impl.events.RoleCreateEvent;
import sx.blah.discord.handle.impl.events.RoleDeleteEvent;
import sx.blah.discord.handle.impl.events.RoleUpdateEvent;
import sx.blah.discord.handle.impl.events.StatusChangeEvent;
import sx.blah.discord.handle.impl.events.UserBanEvent;
import sx.blah.discord.handle.impl.events.UserJoinEvent;
import sx.blah.discord.handle.impl.events.UserLeaveEvent;
import sx.blah.discord.handle.impl.events.UserPardonEvent;
import sx.blah.discord.handle.impl.events.UserRoleUpdateEvent;
import sx.blah.discord.handle.impl.events.UserUpdateEvent;
import sx.blah.discord.handle.obj.IGuild;
import util.Utils;

/**
 * The event listener for logging events
 * @author King
 *
 */
public class LoggingListener {

	BotEventListener botListener;
	
	private static final Logger logger = LogManager.getLogger(LoggingListener.class);

	/**
	 * Static Channel Id for the loggingChannel. Will remove in future update
	 * TODO
	 */
	private static String loggingChannel = "216018960531980298";

	/**
	 * Static reference to time formatter
	 */
	private SimpleDateFormat timeFormat = new SimpleDateFormat("KK:mm:ss z");

	/**
	 * Default constructor 
	 * @param listener
	 */
	public LoggingListener(BotEventListener listener){
		botListener = listener;
	}

	/**
	 * Logs message when a new Channel is created
	 * @param event
	 */
	@EventSubscriber
	public void onChannelCreate(ChannelCreateEvent event){

		Utils.writeMessageToChannel("`" + timeFormat.format(new Date()) + "`" + " The channel **#" + event.getChannel().getName() + "** was created.", event.getChannel().getGuild().getChannelByID(botListener.getServerInfo(event.getChannel().getGuild().getID()).getLoggingChannelId()));

	}

	/**
	 * Logs message when channel is deleted
	 * @param event
	 */
	@EventSubscriber
	public void onChannelDelete(ChannelDeleteEvent event){
		Utils.writeMessageToChannel("`" + timeFormat.format(new Date()) + "`" + " The channel **#" + event.getChannel().getName() + "** was d.", Authorization.client.getChannelByID(loggingChannel));
	}

	/**
	 * Logs Message when a channel is updated
	 * @param event
	 */
	@EventSubscriber
	public void onChannelUpdate(ChannelUpdateEvent event){
		if(!event.getOldChannel().getName().equals(event.getNewChannel().getName())){
			Utils.writeMessageToChannel("`" + timeFormat.format(new Date()) + "`" + " The channel **#" + event.getOldChannel().getName() +"** was changed to **#" + event.getNewChannel().getName(), Authorization.client.getChannelByID(loggingChannel));
		} else if(event.getOldChannel().getTopic() == null && event.getNewChannel().getTopic() != null) {
			Utils.writeMessageToChannel("`" + timeFormat.format(new Date()) + "`" + " The channel **#" + event.getOldChannel().getName() +"** topic was changed to __*" + event.getNewChannel().getTopic() +"*__", Authorization.client.getChannelByID(loggingChannel));
		} else if(!event.getOldChannel().getTopic().equals(event.getNewChannel().getTopic())){

			Utils.writeMessageToChannel("`" + timeFormat.format(new Date()) + "`" + " The channel **#" + event.getOldChannel().getName() +"** topic was changed from __*" + event.getOldChannel().getTopic() + "*__ to __*" + event.getNewChannel().getTopic() +"*__", Authorization.client.getChannelByID(loggingChannel));
		} 
	}

	/**
	 * Logs Message when a message is deleted
	 * @param event
	 */
	@EventSubscriber
	public void onMessageDelete(MessageDeleteEvent event){
		Utils.writeMessageToChannel("`" + timeFormat.format(new Date()) + "` __**" + event.getMessage().getAuthor().getName() + "'s**__ Message: `" + event.getMessage() + "` was deleted in __*#" + event.getMessage().getChannel().getName() + "*__", Authorization.client.getChannelByID(loggingChannel));
	}

	/**
	 * Logs message when a message is pinned
	 * @param event
	 */
	@EventSubscriber
	public void onMessagePin(MessagePinEvent event){
		Utils.writeMessageToChannel("`" + timeFormat.format(new Date()) + "` __**" + event.getMessage().getAuthor().getName() + "**__ Message: " + event.getMessage() + " was pinned in channel: __*#" + event.getChannel().getName() + "*__",  Authorization.client.getChannelByID(loggingChannel));
	}

	/**
	 * Logs message when a message is updated
	 * @param event
	 */
	@EventSubscriber
	public void onMessageUpdate(MessageUpdateEvent event){
		if(event.getNewMessage().getAuthor().getClient() == Authorization.client){
			Utils.writeMessageToChannel("`" + timeFormat.format(new Date()) + "` __**" + event.getOldMessage().getAuthor().getName() + "**__ Message was updated: \n **Before:** " + event.getOldMessage().getContent() + " \n **After:** " + event.getNewMessage().getContent(),  Authorization.client.getChannelByID(loggingChannel));
		}
		
	}

	/**
	 * Logs message when a role is created
	 * @param event
	 */
	@EventSubscriber
	public void onRoleCreate(RoleCreateEvent event){
		Utils.writeMessageToChannel("`" + timeFormat.format(new Date()) + "`" + " Role: " + event.getRole() + " has been created.", Authorization.client.getChannelByID(loggingChannel));
		//TODO fix 
	}

	/**
	 * Logs message when a role is deleted
	 * @param event
	 */
	@EventSubscriber
	public void onRoleDelete(RoleDeleteEvent event){
		Utils.writeMessageToChannel("`" + timeFormat.format(new Date()) + "`" + " Role: " + event.getRole() + " has been deleted.", Authorization.client.getChannelByID(loggingChannel));
	}

	/**
	 * Logs message when a role is updated
	 * @param event
	 */
	@EventSubscriber
	public void onRoleUpdate(RoleUpdateEvent event){
		Utils.writeMessageToChannel("`" + timeFormat.format(new Date()) + "`" + " Role: " + event.getOldRole() + " has been updated to " + event.getNewRole(), Authorization.client.getChannelByID(loggingChannel));
	}

	/**
	 * Logs message when a user is banned
	 * @param event
	 */
	@EventSubscriber
	public void onUserBan(UserBanEvent event){
		Utils.writeMessageToChannel("`" + timeFormat.format(new Date()) + "`" + " The user: __**" + event.getUser().getName() + "**__ was banned from the server", Authorization.client.getChannelByID(loggingChannel));
	}

	/**
	 * Logs message when a user joins the server
	 * @param event
	 */
	@EventSubscriber
	public void onUserJoin(UserJoinEvent event){
		Utils.writeMessageToChannel("`" + timeFormat.format(new Date()) + "`" + " The user: __**" + event.getUser().getName() + "**__ has joined the server", Authorization.client.getChannelByID(loggingChannel));
	}

	/**
	 * Logs message when a user leaves the server
	 * @param event
	 */
	@EventSubscriber
	public void onUserLeave(UserLeaveEvent event){
		Utils.writeMessageToChannel("`" + timeFormat.format(new Date()) + "`" + " The user: __**" + event.getUser().getName() + "**__ has left the server", Authorization.client.getChannelByID(loggingChannel));
	}

	/**
	 * Logs message when a user is unbanned
	 * @param event
	 */
	@EventSubscriber
	public void onUserPardon(UserPardonEvent event){
		Utils.writeMessageToChannel("`" + timeFormat.format(new Date()) + "`" + " The user: __**" + event.getUser().getName() + "**__ was unbanned from the server", Authorization.client.getChannelByID(loggingChannel));
	}

	/**
	 * Logs a message when a user has its role updated
	 * @param event
	 */
	@EventSubscriber
	public void onUserRoleUpdate(UserRoleUpdateEvent event){
		logger.warn("User Role Update " + event.getUser());
	}

	/**
	 * Logs message when a user updates there profile
	 * @param event
	 */
	@EventSubscriber
	public void onUserUpdate(UserUpdateEvent event){
		logger.warn("User Update " );
		String message = null;
		boolean update = false;
		if(!event.getOldUser().getName().equals(event.getNewUser().getName())){
			message = event.getOldUser().getName() + " has changed there name to: " + event.getNewUser().getName();
			update = true;

		} else if (!event.getOldUser().getAvatarURL().equals(event.getNewUser().getAvatarURL())){
			message = event.getNewUser().getName() + " has changed avatars \n **Before:** " + event.getOldUser().getAvatarURL() + "\n **After:** " + event.getNewUser().getAvatarURL();
			update = true;
		}
		if(update){
			for(IGuild guild : event.getClient().getGuilds()){
				ServerInfo info = botListener.getServerInfo(guild.getID());
				if(info != null && info.getLoggingChannelId() != null){
					Utils.writeMessageToChannel(message, guild.getChannelByID(info.getLoggingChannelId()));
				}
			}
		}

	}

	/**
	 * Logs message when somebody starts playing WoW
	 * @param event
	 */
	@EventSubscriber
	public void onStatusChangeEvent(StatusChangeEvent event){
		if(event.getNewStatus().getStatusMessage().toLowerCase().contains("world of warcraft")){
			Utils.writeMessageToChannel("QUIT PLAYING WoW!!!! " + event.getUser().mention(), Authorization.client.getChannelByID(loggingChannel));
		}


	}

}
