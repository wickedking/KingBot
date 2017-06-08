package com.wicked.king.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "people")
public class Person {
	
	@Id
	private String id;
	
	private String userid;
	
	private String name;
	
	private int level;
	
	private int xp;
	
	private int xpNextRank;

	/**
	 * 
	 */
	public Person() {
		//blank constructor
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the xp
	 */
	public int getXp() {
		return xp;
	}

	/**
	 * @param xp the xp to set
	 */
	public void setXp(int xp) {
		this.xp = xp;
	}

	public int getXpNextRank() {
		return xpNextRank;
	}

	public void setXpNextRank(int xpNextRank) {
		this.xpNextRank = xpNextRank;
	}
	

}
