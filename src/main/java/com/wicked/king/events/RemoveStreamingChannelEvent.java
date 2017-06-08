package com.wicked.king.events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * 
 * @author King
 *
 */
public class RemoveStreamingChannelEvent extends CustomEvent {

    /**
     * 
     * @param message
     */
    public RemoveStreamingChannelEvent(IMessage message) {
        super(message);
    }

}
