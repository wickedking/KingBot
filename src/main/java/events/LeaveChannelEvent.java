package events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * 
 * @author King
 *
 */
public class LeaveChannelEvent extends CustomEvent {

	/**
	 * 
	 * @param message
	 */
	public LeaveChannelEvent(IMessage message) {
		super(message);
	}

}
