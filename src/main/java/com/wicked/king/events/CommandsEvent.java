package com.wicked.king.events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * Event for when a command for a Commands is given.
 * 
 * @author King
 *
 */
public class CommandsEvent extends CustomEvent {

    /**
     * 
     * @param message
     */
    public CommandsEvent(final IMessage message) {
        super(message);
    }

}
