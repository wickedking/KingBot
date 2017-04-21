package com.wicked.king.events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * 
 * @author King
 *
 */
public class AdviceEvent extends CustomEvent {

	/**
	 * 
	 * @param message
	 */
	public AdviceEvent(IMessage message) {
		super(message);
		
	}

}
