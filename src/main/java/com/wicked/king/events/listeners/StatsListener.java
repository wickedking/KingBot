package com.wicked.king.events.listeners;

import org.springframework.beans.factory.annotation.Autowired;

import com.wicked.king.db.DBAccessorStats;
import com.wicked.king.events.EmojiEvent;
import com.wicked.king.events.UniqueMessageEvent;

import sx.blah.discord.api.events.EventSubscriber;

public class StatsListener {
    
    @Autowired
    private DBAccessorStats repository;

    public StatsListener(DBAccessorStats repository2) {
        repository = repository2;
    }
    
    @EventSubscriber
    public void recordEmoji(EmojiEvent event){
        
    }
    
    @EventSubscriber
    public void recordUniqueMessage(UniqueMessageEvent event){
        
    }
       

}
