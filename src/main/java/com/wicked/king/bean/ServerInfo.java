package com.wicked.king.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class to hold information about servers and relavent info
 * @author King
 *
 */
@Document(collection = "serverinfo")
public class ServerInfo {
	
	/**
	 * Id used for database
	 */
    @Id
	private String id;
	
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
	private boolean doLogging;
	
	/**
	 * Is annoucements enabled for this server
	 */
	private boolean doAnnouncements;

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
		return doAnnouncements;
	}

	/**
	 * 
	 * @param doAnnouncements
	 */
	public void setDoAnnouncements(boolean doAnnouncements) {
		this.doAnnouncements = doAnnouncements;
	}
	
	
	
	
	

}
