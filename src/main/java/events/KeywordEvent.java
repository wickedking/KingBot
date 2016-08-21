package events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * Event for when a command for a Keyword is given
 * @author King
 *
 */
public class KeywordEvent extends CustomEvent{

	public KeywordEvent(IMessage message){
		super(message);
	}

}
