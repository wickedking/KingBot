package events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * 
 * @author King
 *
 */
public class JokeEvent extends CustomEvent {

	/**
	 * 
	 * @param message
	 */
	public JokeEvent(IMessage message) {
		super(message);
		
	}

}
