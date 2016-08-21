package events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * Event for when a command for a Prune is given
 * @author King
 *
 */
public class PruneEvent extends CustomEvent{

	public PruneEvent(IMessage message){
		super(message);
	}

}
