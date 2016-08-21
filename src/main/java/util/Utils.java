package util;

import java.util.Map;

import login.Authorization;
import sx.blah.discord.handle.impl.obj.PrivateChannel;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;
import sx.blah.discord.util.RequestBuffer;

public class Utils {
	
	
	public void DMUser() {

	}

	public void saveKeywords() {

	}

	public Map<String, String> getKeywords(){

		return null;
	}

	public static void WriteMessageToChannel(String message, IChannel channel){
		//edit message to remove the actual mentions so logging doesnt mention people
		RequestBuffer.request(() -> {
			MessageBuilder builder = new MessageBuilder(Authorization.client).withContent(message);
			builder.withChannel(channel);
			try {
				builder.build();
			} catch (DiscordException | MissingPermissionsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

	}
	
	public static void PrivateMessageUser(IUser user, String message){
		PrivateChannel dm = new PrivateChannel(Authorization.client, user, message);
		try {
			dm.sendMessage("blargh");
		} catch (RateLimitException | MissingPermissionsException | DiscordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
