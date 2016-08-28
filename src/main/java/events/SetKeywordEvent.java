package events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * Event when a keyword is set
 * @author King
 *
 */
public class SetKeywordEvent extends CustomEvent {

	public SetKeywordEvent(IMessage message) {
		super(message);
	}
	
	

}
