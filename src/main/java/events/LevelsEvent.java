package events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * Event for when a command for a levels is given
 * @author King
 *
 */
public class LevelsEvent extends CustomEvent{

	public LevelsEvent(IMessage message){
		super(message);
	}

}
