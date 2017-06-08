package com.wicked.king.events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * 
 * @author King
 *
 */
public class AdminCommandsEvent extends CustomEvent {

	/**
	 * 
	 * @param message
	 */
	public AdminCommandsEvent(IMessage message) {
		super(message);
	}

}
