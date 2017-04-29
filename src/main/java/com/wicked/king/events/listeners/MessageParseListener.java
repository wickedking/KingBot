package com.wicked.king.events.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.wicked.king.constants.BotConstants;
import com.wicked.king.db.DBAccessorPerson;
import com.wicked.king.events.AboutEvent;
import com.wicked.king.events.AdminCommandsEvent;
import com.wicked.king.events.AdviceEvent;

import com.wicked.king.events.BanEvent;
import com.wicked.king.events.CommandsEvent;
import com.wicked.king.events.CreatePollEvent;
import com.wicked.king.events.EightBallEvent;
import com.wicked.king.events.GetPollEvent;
import com.wicked.king.events.GetServerInfoEvent;
import com.wicked.king.events.HelpEvent;
import com.wicked.king.events.InsultEvent;
import com.wicked.king.events.JokeEvent;
import com.wicked.king.events.KickEvent;
import com.wicked.king.events.LeaveChannelEvent;
import com.wicked.king.events.LennyEvent;
import com.wicked.king.events.MiddleFingerEvent;
import com.wicked.king.events.PrivateMessageEvent;
import com.wicked.king.events.PruneEvent;
import com.wicked.king.events.ShrugEvent;
import com.wicked.king.events.TableFlipEvent;
import com.wicked.king.events.TimeoutEvent;
import com.wicked.king.events.UnTableFlipEvent;


import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class MessageParseListener {
	
	private static final Logger logger = LogManager.getLogger(BotEventListener.class);
	
	@Autowired
	private DBAccessorPerson repository;
	
	public MessageParseListener(DBAccessorPerson repository2){
		repository = repository2;
	}
	
	/** 
	 * MessageReceivedEvent listener method, parses message and fires new event if needed
	 * Really need to refactor 
	 * @param event
	 */
	@EventSubscriber
	public void onMessageReceived(MessageReceivedEvent event) {


//		
//		Person author = repository.findById(event.getMessage().getAuthor().getStringID());
//		if (author == null){
//			author = new Person();
//			IUser user = event.getMessage().getAuthor();
//			author.setId(user.getStringID());
//			author.setLevel(1);
//			author.setName(user.getDisplayName(event.getMessage().getGuild()));
//			author.setXp(0);
//			author.setXpNextRank(5);
//			
//		}
//		author.setXp(author.getXp() + 10);
//		repository.save(author);
		
		//TODO refactor, please just refactor this
		if(event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "prune")){
			event.getClient().getDispatcher().dispatch(new PruneEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "getpoll")) {
			logger.warn("creating poll event");
			event.getClient().getDispatcher().dispatch(new GetPollEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "timeout ")) {
			event.getClient().getDispatcher().dispatch(new TimeoutEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "commands")) {
			event.getClient().getDispatcher().dispatch(new CommandsEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "kick ")) {
			event.getClient().getDispatcher().dispatch(new KickEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "ban ")) {
			event.getClient().getDispatcher().dispatch(new BanEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith("/shrug")) {
			event.getClient().getDispatcher().dispatch(new ShrugEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "about")) {
			event.getClient().getDispatcher().dispatch(new AboutEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "help")) {
			event.getClient().getDispatcher().dispatch(new HelpEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "8ball")) {
			event.getClient().getDispatcher().dispatch(new EightBallEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith("/tableflip")) {
			event.getClient().getDispatcher().dispatch(new TableFlipEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith("/unflip")) {
			event.getClient().getDispatcher().dispatch(new UnTableFlipEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "joke")) {
			event.getClient().getDispatcher().dispatch(new JokeEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "insult")) {
			event.getClient().getDispatcher().dispatch(new InsultEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "advice")) {
			event.getClient().getDispatcher().dispatch(new AdviceEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "privatemessage")) {
			event.getClient().getDispatcher().dispatch(new PrivateMessageEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "admincommands")) {
			event.getClient().getDispatcher().dispatch(new AdminCommandsEvent(event.getMessage()));
			
		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "serverinfo")) {
			event.getClient().getDispatcher().dispatch(new GetServerInfoEvent(event.getMessage()));
			
		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "createpoll")) {
			event.getClient().getDispatcher().dispatch(new CreatePollEvent(event.getMessage()));
			
		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "lenny") || event.getMessage().getContent().startsWith("/lenny")) {
			event.getClient().getDispatcher().dispatch(new LennyEvent(event.getMessage()));
			
		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "middlefinger") || event.getMessage().getContent().startsWith("/middlefinger")) {
			event.getClient().getDispatcher().dispatch(new MiddleFingerEvent(event.getMessage()));
			
		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "leavechannel")) {
			event.getClient().getDispatcher().dispatch(new LeaveChannelEvent(event.getMessage()));
			
		}
	}

}
