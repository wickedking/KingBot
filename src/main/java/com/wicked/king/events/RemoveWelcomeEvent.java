package com.wicked.king.events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * 
 * @author King
 *
 */
public class RemoveWelcomeEvent extends CustomEvent {

    /**
     * 
     * @param message
     */
    public RemoveWelcomeEvent(IMessage message) {
        super(message);
    }

}
