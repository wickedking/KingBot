package com.wicked.king.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Bean implementation of a Person.
 * 
 * @author King
 *
 */
@Document(collection = "people")
public class Person {
	
    /**
     * The DB implementation of an Id
     */
	@Id
	private String id;
	
	/**
	 * The userid of the discord user
	 */
	private String userid;
	
	/**
	 * The name of the user
	 */
	private String name;
	
	/**
	 * The XP level of the user
	 */
	private int level;
	
	/**
	 * The total xp of the user
	 */
	private int xp;
	
	/**
	 * The xp needed for next level
	 */
	private int xpNextRank;

	/**
	 * 
	 */
	public Person() {
		//blank constructor
	}
	
	/**
	 * 
	 * @return String The Id
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

	/**
	 * 
	 * @return Xp to next rank
	 */
	public int getXpNextRank() {
		return xpNextRank;
	}

	/**
	 * 
	 * @param xpNextRank
	 */
	public void setXpNextRank(int xpNextRank) {
		this.xpNextRank = xpNextRank;
	}
	

}
