package events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * Event when someones deletes a keyword
 * @author King
 *
 */
public class DeleteKeywordEvent extends CustomEvent {

	public DeleteKeywordEvent(IMessage message) {
		super(message);

	}

}
