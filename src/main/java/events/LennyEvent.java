package events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * 
 * @author King
 *
 */
public class LennyEvent extends CustomEvent {

	/**
	 * 
	 * @param message
	 */
	public LennyEvent(IMessage message) {
		super(message);
	}

}
