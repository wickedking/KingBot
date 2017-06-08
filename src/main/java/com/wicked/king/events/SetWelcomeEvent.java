package com.wicked.king.events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * 
 * @author King
 *
 */
public class SetWelcomeEvent extends CustomEvent {

    /**
     * 
     * @param message
     */
    public SetWelcomeEvent(IMessage message) {
        super(message);
    }

}
