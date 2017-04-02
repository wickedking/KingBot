package events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * 
 * @author King
 *
 */
public class GetServerInfoEvent extends CustomEvent {

	/**
	 * 
	 * @param message
	 */
	public GetServerInfoEvent(IMessage message) {
		super(message);
	}

}
