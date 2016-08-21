package eventListeners;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
import sx.blah.discord.handle.impl.events.UserBanEvent;
import sx.blah.discord.handle.impl.events.UserJoinEvent;
import sx.blah.discord.handle.impl.events.UserLeaveEvent;
import sx.blah.discord.handle.impl.events.UserPardonEvent;
import sx.blah.discord.handle.impl.events.UserRoleUpdateEvent;
import sx.blah.discord.handle.impl.events.UserUpdateEvent;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;
import util.Utils;

public class LoggingListener {

	private static String loggingChannel = "216018960531980298";

	private static SimpleDateFormat timeFormat = new SimpleDateFormat("KK:mm:ss z");


	@EventSubscriber
	public void onChannelCreate(ChannelCreateEvent event){
		Utils.WriteMessageToChannel("`" + timeFormat.format(new Date()) + "`" + " The channel **#" + event.getChannel().getName() + "** was created.", Authorization.client.getChannelByID(loggingChannel));

	}

	@EventSubscriber
	public void onChannelDelete(ChannelDeleteEvent event){
		Utils.WriteMessageToChannel("`" + timeFormat.format(new Date()) + "`" + " The channel **#" + event.getChannel().getName() + "** was created.", Authorization.client.getChannelByID(loggingChannel));
	}

	@EventSubscriber
	public void onChannelUpdate(ChannelUpdateEvent event){
		if(!event.getOldChannel().getName().equals(event.getNewChannel().getName())){
			Utils.WriteMessageToChannel("`" + timeFormat.format(new Date()) + "`" + " The channel **#" + event.getOldChannel().getName() +"** was changed to **#" + event.getNewChannel().getName(), Authorization.client.getChannelByID(loggingChannel));
		} else if(event.getOldChannel().getTopic() == null && event.getNewChannel().getTopic() != null) {
			Utils.WriteMessageToChannel("`" + timeFormat.format(new Date()) + "`" + " The channel **#" + event.getOldChannel().getName() +"** topic was changed to __*" + event.getNewChannel().getTopic() +"*__", Authorization.client.getChannelByID(loggingChannel));
		} else if(!event.getOldChannel().getTopic().equals(event.getNewChannel().getTopic())){

			Utils.WriteMessageToChannel("`" + timeFormat.format(new Date()) + "`" + " The channel **#" + event.getOldChannel().getName() +"** topic was changed from __*" + event.getOldChannel().getTopic() + "*__ to __*" + event.getNewChannel().getTopic() +"*__", Authorization.client.getChannelByID(loggingChannel));
		} 
	}

	@EventSubscriber
	public void onMessageDelete(MessageDeleteEvent event){
		Utils.WriteMessageToChannel("`" + timeFormat.format(new Date()) + "` __**" + event.getMessage().getAuthor().getName() + "'s**__ Message: \"" + event.getMessage() + "\" was deleted in __*#" + event.getMessage().getChannel().getName() + "*__", Authorization.client.getChannelByID(loggingChannel));
	}

	@EventSubscriber
	public void onMessagePin(MessagePinEvent event){
		Utils.WriteMessageToChannel("`" + timeFormat.format(new Date()) + "` __**" + event.getMessage().getAuthor().getName() + "**__ Message: " + event.getMessage() + " was pinned in channel: __*#" + event.getChannel().getName() + "*__",  Authorization.client.getChannelByID(loggingChannel));
	}
	
	@EventSubscriber
	public void onMessageUpdate(MessageUpdateEvent event){
		Utils.WriteMessageToChannel("`" + timeFormat.format(new Date()) + "` __**" + event.getOldMessage().getAuthor().getName() + "**__ Message was updated: \n **Before:** " + event.getOldMessage().getContent() + " \n **After:** " + event.getNewMessage().getContent(),  Authorization.client.getChannelByID(loggingChannel));
	}

	@EventSubscriber
	public void onRoleCreate(RoleCreateEvent event){
		Utils.WriteMessageToChannel("`" + timeFormat.format(new Date()) + "`" + " Role: " + event.getRole() + " has been created.", Authorization.client.getChannelByID(loggingChannel));
	}

	@EventSubscriber
	public void onRoleDelete(RoleDeleteEvent event){
		Utils.WriteMessageToChannel("`" + timeFormat.format(new Date()) + "`" + " Role: " + event.getRole() + " has been deleted.", Authorization.client.getChannelByID(loggingChannel));
	}


	@EventSubscriber
	public void onRoleUpdate(RoleUpdateEvent event){
		Utils.WriteMessageToChannel("`" + timeFormat.format(new Date()) + "`" + " Role: " + event.getOldRole() + " has been updated to " + event.getNewRole(), Authorization.client.getChannelByID(loggingChannel));
	}

	@EventSubscriber
	public void onUserBan(UserBanEvent event){
		Utils.WriteMessageToChannel("`" + timeFormat.format(new Date()) + "`" + " The user: __**" + event.getUser().getName() + "**__ was banned from the server", Authorization.client.getChannelByID(loggingChannel));
	}

	@EventSubscriber
	public void onUserJoin(UserJoinEvent event){
		Utils.WriteMessageToChannel("`" + timeFormat.format(new Date()) + "`" + " The user: __**" + event.getUser().getName() + "**__ has joined the server", Authorization.client.getChannelByID(loggingChannel));
	}

	@EventSubscriber
	public void onUserLeave(UserLeaveEvent event){
		Utils.WriteMessageToChannel("`" + timeFormat.format(new Date()) + "`" + " The user: __**" + event.getUser().getName() + "**__ has left the server", Authorization.client.getChannelByID(loggingChannel));
	}

	@EventSubscriber
	public void onUserPardon(UserPardonEvent event){
		Utils.WriteMessageToChannel("`" + timeFormat.format(new Date()) + "`" + " The user: __**" + event.getUser().getName() + "**__ was unbanned from the server", Authorization.client.getChannelByID(loggingChannel));
	}


	@EventSubscriber
	public void onUserRoleUpdate(UserRoleUpdateEvent event){
		System.out.println("User Role Update " + event.getUser());
	}

	@EventSubscriber
	public void onUserUpdate(UserUpdateEvent event){
		System.out.println("User Update " );
		//new MessageBuilder(Authorization.client).withChannel(loggingChannel).withContent(event.getOldUser(). + event.getNewUser()).build();
	}
}
