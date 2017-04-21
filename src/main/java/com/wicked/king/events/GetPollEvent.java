package com.wicked.king.events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * 
 * @author King
 *
 */
public class GetPollEvent extends CustomEvent {

	/**
	 * 
	 * @param message
	 */
	public GetPollEvent(IMessage message) {
		super(message);
	}

}
