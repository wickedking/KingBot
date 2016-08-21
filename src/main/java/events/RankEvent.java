package events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * Event for when a command for a Rank is given
 * @author King
 *
 */
public class RankEvent extends CustomEvent{
	public RankEvent(IMessage message){
		super(message);
	}
}
