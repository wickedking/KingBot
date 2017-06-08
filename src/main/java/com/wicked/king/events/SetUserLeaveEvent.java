package com.wicked.king.events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * 
 * @author King
 *
 */
public class SetUserLeaveEvent extends CustomEvent {

    /**
     * 
     * @param message
     */
    public SetUserLeaveEvent(IMessage message) {
        super(message);
    }

}
