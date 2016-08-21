package events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * Event for when a command for a ServerInfo is given
 * @author King
 *
 */
public class ServerInfoEvent extends CustomEvent{
	
	public ServerInfoEvent(IMessage message){
		super(message);
	}

}
