package com.wicked.king.events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * 
 * @author King
 *
 */
public class ReminderEvent extends CustomEvent {

    /**
     * 
     * @param message
     */
    public ReminderEvent(IMessage message) {
        super(message);
    }

}
