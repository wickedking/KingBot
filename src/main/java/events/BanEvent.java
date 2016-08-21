package events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * Event for when a command for a Ban is given
 * @author King
 *
 */
public class BanEvent extends CustomEvent{

	public BanEvent(IMessage message){
		super(message);
	}
}
