package com.wicked.king.events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * 
 * @author King
 *
 */
public class RemoveUserLeaveEvent extends CustomEvent {

    /**
     * 
     * @param message
     */
    public RemoveUserLeaveEvent(IMessage message) {
        super(message);
    }

}
