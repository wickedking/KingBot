package events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * Event for deleting logging channel
 * @author King
 *
 */
public class DeleteLoggingEvent extends CustomEvent {

	public DeleteLoggingEvent(IMessage message) {
		super(message);
	}

}
