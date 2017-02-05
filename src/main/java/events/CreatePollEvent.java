package events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * 
 * @author King
 *
 */
public class CreatePollEvent extends CustomEvent {

	/**
	 * 
	 * @param message
	 */
	public CreatePollEvent(IMessage message) {
		super(message);
	}

}
