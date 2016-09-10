package events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * Event for setting the logging channel
 * @author King
 *
 */
public class SetLoggingEvent extends CustomEvent {

	public SetLoggingEvent(IMessage message) {
		super(message);
	}

}
