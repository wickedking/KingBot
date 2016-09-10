package events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * Event for getting the logging channel
 * @author King
 *
 */
public class GetLoggingEvent extends CustomEvent {

	public GetLoggingEvent(IMessage message) {
		super(message);
	}

}
