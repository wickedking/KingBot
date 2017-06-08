package com.wicked.king.events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * 
 * @author King
 *
 */
public class RemoveLoggingEvent extends CustomEvent {

    /**
     * 
     * @param message
     */
    public RemoveLoggingEvent(IMessage message) {
        super(message);
    }

}
