package events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * Event for when a command for a Commands is given
 * @author King
 *
 */
public class CommandsEvent extends CustomEvent{
	
	public CommandsEvent(IMessage message){
		super(message);
	}

}
