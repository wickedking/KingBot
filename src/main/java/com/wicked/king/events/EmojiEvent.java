package com.wicked.king.events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * 
 * @author King
 *
 */
public class EmojiEvent extends CustomEvent {

    /**
     * 
     * @param message
     */
    public EmojiEvent(IMessage message) {
        super(message);
    }

}
