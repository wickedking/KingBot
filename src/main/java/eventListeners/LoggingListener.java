package eventListeners;

import login.Authorization;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ChannelCreateEvent;
import sx.blah.discord.handle.impl.events.ChannelDeleteEvent;
import sx.blah.discord.handle.impl.events.ChannelUpdateEvent;
import sx.blah.discord.handle.impl.events.MessageDeleteEvent;
import sx.blah.discord.handle.impl.events.MessagePinEvent;
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

public class LoggingListener {
	
	private static String loggingChannel = "216018960531980298";
	
	
	@EventSubscriber
	public void onChannelCreate(ChannelCreateEvent event){
		
	}
	
	@EventSubscriber
	public void onChannelDelete(ChannelDeleteEvent event){
		
	}
	
	@EventSubscriber
	public void onChannelUpdate(ChannelUpdateEvent event){
		
	}
	
	@EventSubscriber
	public void onMessageDelete(MessageDeleteEvent event){
		
	}
	
	@EventSubscriber
	public void onMessagePin(MessagePinEvent event){
		
	}
	
	@EventSubscriber
	public void onRoleCreate(RoleCreateEvent event){
		try {
			new MessageBuilder(Authorization.client).withChannel(loggingChannel).withContent("Role: " + event.getRole() + " has been created.").build();
		} catch (RateLimitException | DiscordException | MissingPermissionsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@EventSubscriber
	public void onRoleDelete(RoleDeleteEvent event){
		try {
			new MessageBuilder(Authorization.client).withChannel(loggingChannel).withContent("Role: " + event.getRole() + " has been deleted.").build();
		} catch (RateLimitException | DiscordException | MissingPermissionsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	
	@EventSubscriber
	public void onRoleUpdate(RoleUpdateEvent event){
		try {
			new MessageBuilder(Authorization.client).withChannel(loggingChannel).withContent("Role: " + event.getOldRole() + " has been updated to " + event.getNewRole()).build();
		} catch (RateLimitException | DiscordException | MissingPermissionsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@EventSubscriber
	public void onUserBan(UserBanEvent event){
		
	}
	
	@EventSubscriber
	public void onUserJoin(UserJoinEvent event){
		
	}
	
	@EventSubscriber
	public void onUserLeave(UserLeaveEvent event){
		
	}
	
	@EventSubscriber
	public void onUserPardon(UserPardonEvent event){
		
	}
	
	@EventSubscriber
	public void onMessagePin(UserJoinEvent event){
		
	}
	
	@EventSubscriber
	public void onUserRoleUpdate(UserRoleUpdateEvent event){
		
	}

	@EventSubscriber
	public void onUserUpdate(UserUpdateEvent event){
		//new MessageBuilder(Authorization.client).withChannel(loggingChannel).withContent(event.getOldUser(). + event.getNewUser()).build();
	}
}
