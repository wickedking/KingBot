package events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * 
 * @author King
 *
 */
public class TableFlipEvent extends CustomEvent {

	/**
	 * 
	 * @param message
	 */
	public TableFlipEvent(IMessage message) {
		super(message);
	}

}
