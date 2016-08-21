package events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * Event for when a command for a Giveaway is given
 * @author King
 *
 */
public class GiveawayEvent extends CustomEvent{
	
	public GiveawayEvent(IMessage message){
		super(message);
	}

}
