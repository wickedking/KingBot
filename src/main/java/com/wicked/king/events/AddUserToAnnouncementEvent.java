package com.wicked.king.events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * 
 * @author King
 *
 */
public class AddUserToAnnouncementEvent extends CustomEvent {

    /**
     * 
     * @param message
     */
    public AddUserToAnnouncementEvent(IMessage message) {
        super(message);
    }

}
