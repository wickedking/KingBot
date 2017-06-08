package com.wicked.king.events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * 
 * @author King
 *
 */
public class RemoveUserfromAnnouncementEvent extends CustomEvent {

    /**
     * 
     * @param message
     */
    public RemoveUserfromAnnouncementEvent(IMessage message) {
        super(message);
    }

}
