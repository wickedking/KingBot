package events;

import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.obj.IMessage;

public class HelloEvent extends Event{
	
	private final IMessage message;
	
	public HelloEvent(IMessage message){
		this.message = message;
	}
	
	public IMessage getMessage(){
		return message;
	}

}
