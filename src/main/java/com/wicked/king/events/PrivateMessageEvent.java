package com.wicked.king.events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * 
 * @author King
 *
 */
public class PrivateMessageEvent extends CustomEvent {

	/**
	 * 
	 * @param message
	 */
	public PrivateMessageEvent(IMessage message) {
		super(message);
		
	}

}
