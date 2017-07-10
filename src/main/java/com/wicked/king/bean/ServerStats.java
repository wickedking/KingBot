package com.wicked.king.bean;

import java.util.HashMap;
import java.util.Map;

public class ServerStats {
    
    private String ServerId;

    private Map<String, Integer> emojiMap = new HashMap<>();
    
    private Map<String, Integer> uniqueUsers = new HashMap<>();
    
    
    public ServerStats() {
        // TODO Auto-generated constructor stub
    }


    public String getServerId() {
        return ServerId;
    }


    public void setServerId(String serverId) {
        ServerId = serverId;
    }


    public Map<String, Integer> getEmojiMap() {
        return emojiMap;
    }


    public void setEmojiMap(Map<String, Integer> emojiMap) {
        this.emojiMap = emojiMap;
    }


    public Map<String, Integer> getUniqueUsers() {
        return uniqueUsers;
    }


    public void setUniqueUsers(Map<String, Integer> uniqueUsers) {
        this.uniqueUsers = uniqueUsers;
    }
    
    

}
