package com.wicked.king.events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * 
 * @author King
 *
 */
public class AwwEvent extends CustomEvent {
    
    /**
     * 
     * @param message
     */
    public AwwEvent(IMessage message) {
        super(message);
    }

}
