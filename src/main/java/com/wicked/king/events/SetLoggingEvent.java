package com.wicked.king.events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * 
 * @author King
 *
 */
public class SetLoggingEvent extends CustomEvent {

    /**
     * 
     * @param message
     */
    public SetLoggingEvent(IMessage message) {
        super(message);
    }

}
