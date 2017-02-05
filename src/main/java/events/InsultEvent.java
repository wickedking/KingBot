package events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * 
 * @author King
 *
 */
public class InsultEvent extends CustomEvent {

	/**
	 * 
	 * @param message
	 */
	public InsultEvent(IMessage message) {
		super(message);
		
	}

}
