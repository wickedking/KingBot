package events;

import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.obj.IMessage;

/**
 * Parent class for all custom events. Should not be fired/listened
 * @author King
 *
 */
public class CustomEvent extends Event{
	protected final IMessage message;

	public CustomEvent(IMessage message){
		this.message = message;
	}

	public IMessage getMessage() {
		return message;
	}
}
