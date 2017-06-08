package com.wicked.king.events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * 
 * @author King
 *
 */
public class SetStreamingChannelEvent extends CustomEvent {

    /**
     * 
     * @param message
     */
    public SetStreamingChannelEvent(IMessage message) {
        super(message);
    }

}
