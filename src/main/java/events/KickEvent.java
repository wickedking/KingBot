package events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * Event for when a command for a Kick is given
 * @author King
 *
 */
public class KickEvent extends CustomEvent{
	
	public KickEvent(IMessage message){
		super(message);
	}

}
