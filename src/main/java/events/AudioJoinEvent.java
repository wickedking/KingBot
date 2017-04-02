package events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * 
 * @author King
 *
 */
public class AudioJoinEvent extends CustomEvent {

	/**
	 * 
	 * @param message
	 */
	public AudioJoinEvent(IMessage message) {
		super(message);
	}

}
