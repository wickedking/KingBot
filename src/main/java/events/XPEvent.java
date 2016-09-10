package events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * Event when applying xp for user
 * @author King
 *
 */
public class XPEvent extends CustomEvent {

	public XPEvent(IMessage message) {
		super(message);
	}

}
