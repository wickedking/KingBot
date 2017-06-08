package com.wicked.king.events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * Event used when asking for help with a command
 * @author King
 *
 */
public class HelpEvent extends CustomEvent {

	public HelpEvent(IMessage message) {
		super(message);
	}

}
