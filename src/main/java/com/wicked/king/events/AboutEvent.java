package com.wicked.king.events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * 
 * @author King
 *
 */
public class AboutEvent extends CustomEvent {

	/**
	 * 
	 * @param message
	 */
	public AboutEvent(IMessage message) {
		super(message);
	}

}
