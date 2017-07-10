package com.wicked.king.events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * 
 * @author King
 *
 */
public class UniqueMessageEvent extends CustomEvent {

    /**
     * 
     * @param message
     */
    public UniqueMessageEvent(IMessage message) {
        super(message);
    }

}
