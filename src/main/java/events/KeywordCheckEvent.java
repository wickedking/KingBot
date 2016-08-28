package events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * Event used to check if keyword is in message
 * @author King
 *
 */
public class KeywordCheckEvent extends CustomEvent{

	public KeywordCheckEvent(IMessage message) {
		super(message);
	}

}
