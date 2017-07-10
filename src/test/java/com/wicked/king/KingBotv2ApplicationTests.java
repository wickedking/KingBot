package com.wicked.king;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wicked.king.bean.Person;
import com.wicked.king.bean.ServerInfo;
import com.wicked.king.bean.UtilString;
import com.wicked.king.constants.BotConstants;
import com.wicked.king.events.ShrugEvent;

import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KingBotv2ApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testPerson(){
	    Person person = new Person();
	    
	    person.setId("testId");
	    person.setLevel(3);
	    person.setName("testName");
	    person.setUserid("testUserId");
	    person.setXp(100);
	    person.setXpNextRank(100);
	    
	    assertEquals("testId", person.getId());
	    
	    assertEquals(3, person.getLevel());
	    assertEquals("testName", person.getName());
	    assertEquals("testUserId", person.getUserid());
	    assertEquals(100, person.getXp());
	    assertEquals(100, person.getXpNextRank());
	    
	}
	
	@Test
	public void testUtilString(){
	    UtilString string = new UtilString();
	    
	    string.setId("testId");
        string.setListType("testList");
        string.setMessage("testMessage");
	    
        assertEquals("testId", string.getId());
        assertEquals("testList",string.getListType());
        assertEquals("testMessage", string.getMessage());
	    
	    
	}
	
	@Test
	public void testServerInfo(){
	    ServerInfo server = new ServerInfo();
	    
	    server.setAnnouncementChannel("testChannel");
	    server.setDoAnnouncements(true);
	    server.setDoLogging(true);
	    server.setId("testId");
	    server.setLoggingChannel("TestLogging");
	    
	    assertEquals("testChannel", server.getAnnouncementChannel());
	    assertEquals(true, server.isDoAnnouncements());
	    assertEquals(true, server.isDoLogging());
	    assertEquals("testId", server.getId());
	    assertEquals("TestLogging", server.getLoggingChannel());
	}
	
	@Test
	public void testBotConstants() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
	    Constructor<BotConstants> constructor = BotConstants.class.getDeclaredConstructor();
	    assertTrue(Modifier.isPrivate(constructor.getModifiers()));
	    constructor.setAccessible(true);
	    constructor.newInstance();
	    
	}
	
	@Test
    public void testAboutEvent(){ 
	    
    }
//	
//	@Test
//    public void test(){
//        
//    }
//	
//	@Test
//    public void test(){
//        
//    }
//	
//	@Test
//    public void test(){
//        
//    }
//	
//	@Test
//    public void test(){
//        
//    }

}
