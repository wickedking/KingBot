package events;

import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.obj.IMessage;

public class RankEvent extends Event{
	private final IMessage message;

	public RankEvent(IMessage message){
		this.message = message;
	}

	public IMessage getMessage(){
		return message;
	}
}
