package events;

import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.obj.IMessage;

public class BanEvent extends Event{
private final IMessage message;
	
	public BanEvent(IMessage message){
		this.message = message;
	}
	
	public IMessage getMessage(){
		return message;
	}
}
