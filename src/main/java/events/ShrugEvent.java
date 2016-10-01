package events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * 
 * @author King
 */
public class ShrugEvent extends CustomEvent {

	/**
	 * @param message
	 */
	public ShrugEvent(IMessage message) {
		super(message);
	}

}
