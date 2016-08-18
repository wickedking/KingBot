package events;

import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.obj.IMessage;

public class GiveawayEvent extends Event{
private final IMessage message;
	
	public GiveawayEvent(IMessage message){
		this.message = message;
	}
	
	public IMessage getMessage(){
		return message;
	}

}
