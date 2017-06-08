package com.wicked.king.events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * Event for when a command for a timeout is given
 * @author King
 *
 */
public class TimeoutEvent extends CustomEvent{
	
	public TimeoutEvent(IMessage message){
		super(message);
	}
	
}
