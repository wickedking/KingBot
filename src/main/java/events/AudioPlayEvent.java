package events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * 
 * @author King
 *
 */
public class AudioPlayEvent extends CustomEvent {

	/**
	 * 
	 * @param message
	 */
	public AudioPlayEvent(IMessage message) {
		super(message);

	}

}
