package com.wicked.king.bean;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class to hold information about servers and relavent info
 * @author King
 *
 */
@Document(collection = "serverinfo")
public class ServerInfo  {
	
	/**
	 * Id used for database
	 */
    @Id
	private String id;
    
    /**
     * Id of the discord server
     */
    private String serverId;
	
	/**
	 * The Id of the logging Channel
	 */
	private String loggingChannel;
	
	/**
	 * The channel id of the announcement channel
	 */
	private String announcementChannel;
	
	/**
	 * Is logging enabled for this server
	 */
	private boolean doLogging = false;
	
	/**
	 * Is announcement enabled for this server
	 */
	private boolean doStreamingAnnouncements = false;
	
	/**
	 * Make an announcement for people joining server
	 */
	private boolean doWelcomeAnnouncements = false;
	
	/**
	 * Make an announcement for poeple leaving the server
	 */
	private boolean doLeaveAnnouncements = false;
	
	/**
	 * Channel to do welcome and leaving announcements on
	 */
	private String welcomeChannel;
	
	/**
	 * Message for welcome announcement
	 */
	private String welcomeMessage;
	
	/**
	 * Message for streaming Announcement
	 */
	private String streamingMessage;
	
	/**
	 * Message for user leave message
	 */
	private String leaveMessage;
	
	/**
	 * Users to announce when streaming
	 */
	private List<String> userIdForAnnouncement = new ArrayList<>();

	/**
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public String getLoggingChannel() {
		return loggingChannel;
	}

	/**
	 * 
	 * @param loggingChannel
	 */
	public void setLoggingChannel(String loggingChannel) {
		this.loggingChannel = loggingChannel;
	}

	/**
	 * 
	 * @return
	 */
	public String getAnnouncementChannel() {
		return announcementChannel;
	}

	/**
	 * 
	 * @param announcementChannel
	 */
	public void setAnnouncementChannel(String announcementChannel) {
		this.announcementChannel = announcementChannel;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isDoLogging() {
		return doLogging;
	}

	/**
	 * 
	 * @param doLogging
	 */
	public void setDoLogging(boolean doLogging) {
		this.doLogging = doLogging;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isDoAnnouncements() {
		return doStreamingAnnouncements;
	}

	/**
	 * 
	 * @param doAnnouncements
	 */
	public void setDoAnnouncements(boolean doAnnouncements) {
		this.doStreamingAnnouncements = doAnnouncements;
	}

    public boolean isDoWelcomeAnnouncements() {
        return doWelcomeAnnouncements;
    }

    public void setDoWelcomeAnnouncements(boolean doWelcomeAnnouncements) {
        this.doWelcomeAnnouncements = doWelcomeAnnouncements;
    }

    public boolean isDoLeaveAnnouncements() {
        return doLeaveAnnouncements;
    }

    public void setDoLeaveAnnouncements(boolean doLeaveAnnouncements) {
        this.doLeaveAnnouncements = doLeaveAnnouncements;
    }

    public String getWelcomeChannel() {
        return welcomeChannel;
    }

    public void setWelcomeChannel(String welcomeChannel) {
        this.welcomeChannel = welcomeChannel;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public List<String> getUserIdForAnnouncement() {
        return userIdForAnnouncement;
    }

    public void addUserIdForAnnouncement(String userIdForAnnouncement) {
        this.userIdForAnnouncement.add(userIdForAnnouncement);
    }
    
    public void removeUserIdForAnnouncement(String userIdForAnnouncement){
        this.userIdForAnnouncement.remove(userIdForAnnouncement);
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public String getStreamingMessage() {
        return streamingMessage;
    }

    public void setStreamingMessage(String streamingMessage) {
        this.streamingMessage = streamingMessage;
    }

    public String getLeaveMessage() {
        return leaveMessage;
    }

    public void setLeaveMessage(String leaveMessage) {
        this.leaveMessage = leaveMessage;
    }
	
	
	
	
	

}
