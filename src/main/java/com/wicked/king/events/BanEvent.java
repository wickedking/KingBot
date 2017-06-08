package com.wicked.king.events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * Event for when a command for a Ban is given.
 *
 * @author King
 *
 */
public class BanEvent extends CustomEvent {

    /**
     *
     * @param message the message
     */
    public BanEvent(final IMessage message) {
        super(message);
    }
}
