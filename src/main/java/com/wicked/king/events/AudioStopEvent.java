package com.wicked.king.events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * 
 * @author King
 *
 */
public class AudioStopEvent extends CustomEvent {

	/**
	 * 
	 * @param message
	 */
	public AudioStopEvent(IMessage message) {
		super(message);
	}

}
