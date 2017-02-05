package eventListeners;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.sound.sampled.UnsupportedAudioFileException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bean.GuildLoggingBean;
import bean.Keyword;
import constants.BotConstants;
import dao.BotDAO;
import details.ServerInfo;
import events.AboutEvent;
import events.AdminCommandsEvent;
import events.AdviceEvent;
import events.BanEvent;
import events.CommandsEvent;
import events.CreatePollEvent;
import events.DeleteKeywordEvent;
import events.DeleteLoggingEvent;
import events.EightBallEvent;
import events.GetLoggingEvent;
import events.GetPollEvent;
import events.GiveawayEvent;
import events.HelpEvent;
import events.InsultEvent;
import events.JokeEvent;
import events.KeywordCheckEvent;
import events.KeywordEvent;
import events.KickEvent;
import events.LevelsEvent;
import events.PlayMusicEvent;
import events.PrivateMessageEvent;
import events.PruneEvent;
import events.RankEvent;
import events.ServerInfoEvent;
import events.SetKeywordEvent;
import events.SetLoggingEvent;
import events.ShrugEvent;
import events.TableFlipEvent;
import events.TimeoutEvent;
import events.UnTableFlipEvent;
import events.XPEvent;
import login.Authorization;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IPrivateChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageList;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;
import sx.blah.discord.util.audio.AudioPlayer;
import util.Utils;

/**
 * Holds all the event listeners in the methods. 
 * @author WickedKing
 *
 */
public class BotEventListener {

	private static final Logger logger = LogManager.getLogger(BotEventListener.class);

	/**
	 * Reference to the Dao layer
	 */
	private BotDAO botDAO = new BotDAO();

	/**
	 * All the servers with the current info
	 */
	Map<String, ServerInfo> servers = new HashMap<>();

	/**
	 * returns the serverinfo for the given server
	 * @param guildId
	 * @return ServerInfo
	 */
	public ServerInfo getServerInfo(String guildId){
		return servers.get(guildId);
	}

	/**
	 * ReadyEvent listener method
	 * @param event
	 */
	@EventSubscriber
	public void onReady(ReadyEvent event) {
		logger.warn("ready method");
		setup();
	}

	/**
	 * setups the data needed on construction
	 */
	private void setup(){
		servers = botDAO.getServersInfo();
		for(ServerInfo server : servers.values()){
			logger.warn(server.getGuildId());
			server.setLoggingChannelId(botDAO.getLoggingChannel(server.getGuildId()));
		}

	}

	/**
	 * Refresh the data from the server based on data update
	 * 
	 * @param guildID
	 */
	private void resetServerInfo(String guildID){
		ServerInfo server = botDAO.getServerInfo(guildID);
		servers.put(guildID, server);
	}

	/**
	 * MessageReceivedEvent listener method, parses message and fires new event if needed
	 * Really need to refactor
	 * @param event
	 */
	@EventSubscriber
	public void onMessageReceived(MessageReceivedEvent event){
		logger.warn(event.getMessage().getAuthor().getID());
		GuildLoggingBean loggingBean = new GuildLoggingBean();
		loggingBean.setGuildId(event.getMessage().getGuild().getID());
		loggingBean.setNumMessages(1);
		LocalTime time = LocalTime.now();
		logger.warn(time.getHour());
		loggingBean.setHour(time.getHour());
		//botDAO.updateLoggingForGuild(loggingBean);

		event.getClient().getDispatcher().dispatch(new XPEvent(event.getMessage()));
		//TODO refactor, please just refactor this
		if(event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "prune")){
			event.getClient().getDispatcher().dispatch(new PruneEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "rank")){
			event.getClient().getDispatcher().dispatch(new RankEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "getpoll")) {
			logger.warn("creating poll event");
			event.getClient().getDispatcher().dispatch(new GetPollEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "levels")){
			event.getClient().getDispatcher().dispatch(new LevelsEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "keywords")) {
			event.getClient().getDispatcher().dispatch(new KeywordEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "timeout ")) {
			event.getClient().getDispatcher().dispatch(new TimeoutEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "giveaway")) {
			event.getClient().getDispatcher().dispatch(new GiveawayEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "commands")) {
			event.getClient().getDispatcher().dispatch(new CommandsEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "kick ")) {
			event.getClient().getDispatcher().dispatch(new KickEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "ban ")) {
			event.getClient().getDispatcher().dispatch(new BanEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "serverinfo")) {
			event.getClient().getDispatcher().dispatch(new ServerInfoEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "setkeyword ")) {
			event.getClient().getDispatcher().dispatch(new SetKeywordEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "deletekeyword ")) {
			event.getClient().getDispatcher().dispatch(new DeleteKeywordEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "setlogging")) {
			event.getClient().getDispatcher().dispatch(new SetLoggingEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "deletelogging")) {
			event.getClient().getDispatcher().dispatch(new DeleteLoggingEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "getlogging")) {
			event.getClient().getDispatcher().dispatch(new GetLoggingEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "playmusic")) {
			event.getClient().getDispatcher().dispatch(new PlayMusicEvent(event.getMessage()));

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

		}  else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "admincommands")) {
			event.getClient().getDispatcher().dispatch(new AdminCommandsEvent(event.getMessage()));

		}  else {
			event.getClient().getDispatcher().dispatch(new KeywordCheckEvent(event.getMessage()));
		}

	}

	/**
	 * Writes the shrug ascii out to the channel
	 * @param event
	 */
	@EventSubscriber
	public void onShrug(ShrugEvent event){
		Utils.writeMessageToChannel(BotConstants.SHRUG, event.getMessage().getChannel());
	}

	/**
	 * Writes the shrug ascii out to the channel
	 * @param event
	 */
	@EventSubscriber
	public void onTableFlip(TableFlipEvent event){
		Utils.writeMessageToChannel(BotConstants.TABLE_FLIP, event.getMessage().getChannel());
	}


	/**
	 * Writes the shrug ascii out to the channel
	 * @param event
	 */
	@EventSubscriber
	public void onUnTableFlip(UnTableFlipEvent event){
		Utils.writeMessageToChannel(BotConstants.UN_TABLE_FLIP, event.getMessage().getChannel());
	}

	/**
	 * Bans the user mentioned
	 * @param event
	 */
	@EventSubscriber
	public void onBan(BanEvent event){
		boolean isAdmin = Utils.isBotAdmin(event.getMessage().getAuthor(), event.getMessage().getGuild().getOwner(), event.getMessage().getAuthor().getRolesForGuild(event.getMessage().getGuild()));
		if(isAdmin){
			IGuild guild = event.getClient().getGuilds().get(0);
			try {
				Authorization.client.getGuildByID(guild.getID()).banUser(event.getMessage().getMentions().get(0));
			} catch (RateLimitException | MissingPermissionsException | DiscordException e) {
				logger.error(e);
			}
		} else{
			Utils.writeMessageToChannel(BotConstants.NOT_AUTHORIZED, event.getMessage().getChannel());
		}
	}

	/**
	 * Kicks the user mentioned
	 * @param event
	 */
	@EventSubscriber
	public void onKick(KickEvent event){

		boolean isAdmin = Utils.isBotAdmin(event.getMessage().getAuthor(), event.getMessage().getGuild().getOwner(), event.getMessage().getAuthor().getRolesForGuild(event.getMessage().getGuild()));
		if(isAdmin){
			try {
				Authorization.client.getGuilds().get(0).kickUser(event.getMessage().getMentions().get(0));
			} catch (RateLimitException | MissingPermissionsException | DiscordException e) {
				logger.error(e);
			}
		} else {
			Utils.writeMessageToChannel(BotConstants.NOT_AUTHORIZED, event.getMessage().getChannel());
		}


	}



	/**
	 * Handles a timeout for the user mentioned
	 * TODO
	 * @param event
	 */
	@EventSubscriber
	public void onTimeout(TimeoutEvent event){
		boolean isAdmin = Utils.isBotAdmin(event.getMessage().getAuthor(), event.getMessage().getGuild().getOwner(), event.getMessage().getAuthor().getRolesForGuild(event.getMessage().getGuild()));
		if(isAdmin){
			Utils.writeMessageToChannel(BotConstants.UNFINISHED, event.getMessage().getChannel());
		}else {
			Utils.writeMessageToChannel(BotConstants.NOT_AUTHORIZED, event.getMessage().getChannel());
		}

	}

	/**
	 * Deletes the requested number of messages from channel requested from
	 * @param event
	 */
	@EventSubscriber
	public void onPrune(PruneEvent event){
		boolean isAdmin = Utils.isBotAdmin(event.getMessage().getAuthor(), event.getMessage().getGuild().getOwner(), event.getMessage().getAuthor().getRolesForGuild(event.getMessage().getGuild()));
		if(isAdmin){
			MessageList list = event.getMessage().getChannel().getMessages();
			String totalMessage = event.getMessage().getContent();
			int index = totalMessage.indexOf(' ');
			int numMessages = Integer.parseInt(totalMessage.substring(index).trim());
			try {
				list.get(0).delete();
				if(numMessages <= 2){
					for(int i = 1; i < numMessages + 1; i++){
						list.get(i).delete();
					}
				} else{
					list.bulkDelete(list.subList(0, numMessages + 1));
				}
			} catch (RateLimitException | DiscordException | MissingPermissionsException e) {
				logger.error(e);
			}
		} else{ //TODO catch parse int error and limit to 100 messages.
			Utils.writeMessageToChannel(BotConstants.NOT_AUTHORIZED, event.getMessage().getChannel());
		}


	}

	/**
	 * Displays the rank of the user mentioned
	 * @param event
	 */
	@EventSubscriber
	public void onRank(RankEvent event){
		String xp = botDAO.getXPForUser(event.getMessage().getGuild().getID(), event.getMessage().getAuthor().getID());
		Utils.writeMessageToChannel(event.getMessage().getAuthor() + " " + xp, event.getMessage().getChannel());
	}

	/**
	 * Displays the levels of all the people that have been logged.
	 * @param event
	 */
	@EventSubscriber
	public void onLevels(LevelsEvent event){
		Utils.writeMessageToChannel(BotConstants.UNFINISHED, Authorization.client.getChannelByID(event.getMessage().getChannel().getID()));
	}

	/**
	 * Displays current Information about the server
	 * @param event
	 */
	@EventSubscriber
	public void onServerInfo(ServerInfoEvent event){
		IGuild server = Authorization.client.getGuilds().get(Authorization.client.getGuilds().indexOf(event.getMessage().getGuild()));
		Utils.writeMessageToChannel("```Server Name: " + server.getName() + "\n Owner: " + server.getOwner().getName() + "\n Number of Channels: " + server.getChannels().size() + "\n Server creation date: " + server.getCreationDate() + "\n Number of Roles: " + server.getRoles().size() + "\n Number of users: " + server.getUsers().size() + "\n Number of Voice Channels: " + server.getVoiceChannels().size() + "```", Authorization.client.getChannelByID(event.getMessage().getChannel().getID()));

	}

	/**
	 * Posts the keywords that have been setup for a filter
	 * @param event
	 */
	@EventSubscriber
	public void onGetKeywords(KeywordEvent event){

		boolean isAdmin = Utils.isBotAdmin(event.getMessage().getAuthor(), event.getMessage().getGuild().getOwner(), event.getMessage().getAuthor().getRolesForGuild(event.getMessage().getGuild()));
		String message = "";
		if(isAdmin){
			List<Keyword> keywords = botDAO.getKeywords(event.getMessage().getGuild().getID());
			if(!keywords.isEmpty()){
				StringBuilder sb = new StringBuilder();
				for(Keyword keyword : keywords){
					sb.append("**Keyword:** " + keyword.getKeyword());
					sb.append(" | **Action1:** " + keyword.getAction1());
					sb.append(" | **Action2:** " + keyword.getAction2()); 
					sb.append(" | **Message:** " + keyword.getMessage());
					sb.append(" \n");

				}
				message = sb.toString();
			} else {
				message = "No keywords are set for this server";
			}
		} else {
			message = BotConstants.NOT_AUTHORIZED;
		}
		
		try {
			IPrivateChannel channel = Authorization.client.getOrCreatePMChannel(event.getMessage().getAuthor());
			Utils.writeMessageToChannel(message, channel);

		} catch (DiscordException | RateLimitException e) {
			logger.error(e);
		}

	}

	/**
	 * Adds the keyword that is specified
	 * @param event
	 */
	@EventSubscriber
	public void onSetKeyword(SetKeywordEvent event){
		boolean isAdmin = Utils.isBotAdmin(event.getMessage().getAuthor(), event.getMessage().getGuild().getOwner(), event.getMessage().getAuthor().getRolesForGuild(event.getMessage().getGuild()));
		if(isAdmin){
			String[] words =  event.getMessage().getContent().split("\\|");
			if(words.length != 5){
				Utils.writeMessageToChannel(BotConstants.BAD_KEYWORD, Authorization.client.getChannelByID(event.getMessage().getChannel().getID()));
				return;
			}
			Keyword keyword = new Keyword();
			keyword.setGuildId(event.getMessage().getGuild().getID());
			keyword.setKeyword(words[1].trim());
			keyword.setAction1(words[2].trim());
			keyword.setAction2(words[3].trim());
			keyword.setMessage(words[4].trim());
			if(validateKeyword(keyword)){
				boolean success =  botDAO.saveKeyword(keyword);
				if(success){
					Utils.writeMessageToChannel("Keyword: " + keyword.getKeyword() + " was saved successfully", Authorization.client.getChannelByID(event.getMessage().getChannel().getID()));
					resetServerInfo(event.getMessage().getGuild().getID());
				} else {
					Utils.writeMessageToChannel("Your keyword attempt was not saved successfully", Authorization.client.getChannelByID(event.getMessage().getChannel().getID()));
				}
			} else {
				Utils.writeMessageToChannel(BotConstants.BAD_KEYWORD, Authorization.client.getChannelByID(event.getMessage().getChannel().getID()));
			}

		} else{
			Utils.writeMessageToChannel(BotConstants.NOT_AUTHORIZED, event.getMessage().getChannel());
		}

	}

	/**
	 * validates the keyword created
	 * @param keyword
	 */
	private boolean validateKeyword(Keyword keyword){
		String action1 = keyword.getAction1();
		String action2 = keyword.getAction2();
		boolean action1Success = Utils.validateAction1(action1);
		boolean action2Success = Utils.validateAction2(action2);

		if(action1Success && action2Success){
			return true;
			//TODO is this what i want to do here?
		} else {
			return false;
		}
	}

	/**
	 * Deletes the specified keyword
	 * @param event
	 */
	@EventSubscriber
	public void onDeleteKeyword(DeleteKeywordEvent event){
		boolean isAdmin = Utils.isBotAdmin(event.getMessage().getAuthor(), event.getMessage().getGuild().getOwner(), event.getMessage().getAuthor().getRolesForGuild(event.getMessage().getGuild()));
		if(isAdmin){
			Keyword keyword = new Keyword();
			keyword.setGuildId(event.getMessage().getGuild().getID());
			keyword.setKeyword(event.getMessage().getContent().replace("`deletekeyword", " ").trim());
			boolean success = botDAO.deleteKeyword(keyword);
			if(success){
				Utils.writeMessageToChannel("Keyword: " + keyword.getKeyword() + " was deleted successfully", Authorization.client.getChannelByID(event.getMessage().getChannel().getID()));
				resetServerInfo(event.getMessage().getGuild().getID());
			} else {
				Utils.writeMessageToChannel(BotConstants.DELETE_KEYWORD_UNSUCCESSFUL, Authorization.client.getChannelByID(event.getMessage().getChannel().getID()));
			}
		} else{
			Utils.writeMessageToChannel(BotConstants.NOT_AUTHORIZED, event.getMessage().getChannel());
		}

	}

	/**
	 * Checks message for keywords and runs action associated
	 * @param event
	 */
	@EventSubscriber
	public void onKeywordCheck(KeywordCheckEvent event){
		String id = event.getMessage().getGuild().getID();
		ServerInfo server = servers.get(id);
		String message = event.getMessage().getContent();
		for(Keyword keyword : server.getKeywords()){
			if(message.toLowerCase().contains(keyword.getKeyword().toLowerCase())){
				evaluateMessage(keyword, event);
				return;
			}
		}
	}

	/**
	 * Evaluates the message according to the keyword rules
	 * @param keyword
	 * @param event
	 */
	private void evaluateMessage(Keyword keyword, KeywordCheckEvent event){
		if(keyword.getAction2().equals(BotConstants.INCLUDED)){
			if(keyword.getAction1().equals(BotConstants.DELETE)){
				try {
					event.getMessage().delete();
					if(keyword.getMessage().equals(BotConstants.NOTHING)){
						//do nothing i guess?
					} else {
						Utils.writeMessageToChannel(keyword.getMessage(), event.getMessage().getChannel());
					}
				} catch (RateLimitException | MissingPermissionsException | DiscordException e) {
					logger.error(e);
				}

			} else if (keyword.getAction1().equals(BotConstants.KEEP)){
				if(keyword.getMessage().equals(BotConstants.NOTHING)){
					//do nothing i guess?
				} else {
					Utils.writeMessageToChannel(keyword.getMessage(), event.getMessage().getChannel());
				}
			}

		} else if (keyword.getAction2().equals(BotConstants.STANDALONE)){
			String message = " " + keyword.getKeyword() + " ";
			if(event.getMessage().getContent().contains(message)){
				if(keyword.getAction1().equals(BotConstants.DELETE)){
					try {
						event.getMessage().delete();
						if(keyword.getMessage().equals(BotConstants.NOTHING)){
							//do nothing i guess?
						} else {
							Utils.writeMessageToChannel(keyword.getMessage(), event.getMessage().getChannel());
						}
					} catch (RateLimitException | MissingPermissionsException | DiscordException e) {
						logger.error(e);
					}

				} else if (keyword.getAction1().equals(BotConstants.KEEP)){
					if(keyword.getMessage().equals(BotConstants.NOTHING)){
						//do nothing i guess?
					} else {
						Utils.writeMessageToChannel(keyword.getMessage(), event.getMessage().getChannel());
					}
				}
			} else {
				return;
			}

		} else {
			logger.error("hmm something happened");
		}
	}

	/**
	 * Updates the xp of the user
	 * @param event
	 */
	@EventSubscriber
	public void onXP(XPEvent event){
		Random rand = new Random();
		botDAO.updateUserXP(event.getMessage().getGuild().getID(), event.getMessage().getAuthor().getID(), rand.nextInt(11) + 20);
	}

	/**
	 * Delete the logging channel saved
	 * @param event
	 */
	@EventSubscriber
	public void onDeleteLogging(DeleteLoggingEvent event){
		boolean success = botDAO.deleteLoggingChannel(event.getMessage().getGuild().getID());
		String message = "";
		if(success){
			message = "Logging will no longer occur";
		} else {
			message = BotConstants.DELETE_LOGGING_UNSUCCESSFUL;
		}
		resetServerInfo(event.getMessage().getGuild().getID());
		Utils.writeMessageToChannel(message, event.getMessage().getChannel());
	}

	/**
	 * Sets the Logging channel
	 * @param event
	 */
	@EventSubscriber
	public void onSetLogging(SetLoggingEvent event){
		boolean success = botDAO.setLoggingChannel(event.getMessage().getGuild().getID(), event.getMessage().getChannel().getID());
		if(success){
			Utils.writeMessageToChannel("Logging has been set to this channel", event.getMessage().getChannel());
		} else {
			Utils.writeMessageToChannel("Error Setting this channel as logging. Check to see if you have already set a logging channel", event.getMessage().getChannel());
		}
		resetServerInfo(event.getMessage().getGuild().getID());
	}

	/**
	 * Returns teh logging channel
	 * @param event
	 */
	@EventSubscriber
	public void onGetLogging(GetLoggingEvent event){
		botDAO.getLoggingChannel(event.getMessage().getGuild().getID());

	}

	/**
	 * Plays music
	 * TODO fix
	 * @param event
	 */
	@EventSubscriber
	public void onPlayMusic(PlayMusicEvent event){
		AudioPlayer player = AudioPlayer.getAudioPlayerForGuild(event.getMessage().getGuild());
		try {
			player.queue(new URL("https://www.youtube.com/watch?v=308KpFZ4cT8"));
			logger.warn("Playing music maybe");
		} catch (IOException | UnsupportedAudioFileException e) {
			logger.error(e);
		}

	}

	/**
	 * Used to provide basic information about the bot.
	 * @param event
	 */
	@EventSubscriber
	public void onAbout(AboutEvent event){
		Utils.writeMessageToChannel(BotConstants.ABOUT_MESSAGE, event.getMessage().getChannel());
	}

	/**
	 * Provides an 8ball answer to a question
	 * @param event
	 */
	@EventSubscriber
	public void onEightBall(EightBallEvent event){
		List<String> eightBall = Utils.getEightBallAnswers();
		Random random = new Random();
		int choice = random.nextInt(eightBall.size());
		Utils.writeMessageToChannel(event.getMessage().getAuthor().mention() + " " + eightBall.get(choice), event.getMessage().getChannel());
	}

	/**
	 * Provides a joke
	 * @param event
	 */
	@EventSubscriber
	public void onJoke(JokeEvent event){
		String joke = Utils.getJoke();
		Utils.writeMessageToChannel(joke, event.getMessage().getChannel());
	}

	/**
	 * Posts usually (not) helpful advice
	 * @param event
	 */
	@EventSubscriber
	public void onAdvice(AdviceEvent event){
		Utils.writeMessageToChannel(Utils.getAdvice(), event.getMessage().getChannel());
	}

	/**
	 * Posts a somewhat nice insult
	 * @param event
	 */
	@EventSubscriber
	public void onInsult(InsultEvent event){
		Utils.writeMessageToChannel(Utils.getInsult(), event.getMessage().getChannel());
	}

	/**
	 * Posts the commands in the channel requested
	 * @param event
	 */
	@EventSubscriber
	public void onCommands(CommandsEvent event){
		try {
			IPrivateChannel channel = Authorization.client.getOrCreatePMChannel(event.getMessage().getAuthor());
			Utils.writeMessageToChannel(BotConstants.HELP_MESSAGE, channel);
			Utils.writeMessageToChannel(event.getMessage().getAuthor().mention() + " The commands have been messaged to you", event.getMessage().getChannel());

		} catch (DiscordException | RateLimitException e) {
			logger.error(e);
		}

	}
	
	/**
	 * Private message the user with the String of admin commands
	 * @param event
	 */
	@EventSubscriber
	public void onAdminCommands(AdminCommandsEvent event){
		try {
			IPrivateChannel channel = Authorization.client.getOrCreatePMChannel(event.getMessage().getAuthor());
			Utils.writeMessageToChannel(BotConstants.ADMIN_HELP_MESSAGE, channel);
			Utils.writeMessageToChannel(event.getMessage().getAuthor().mention() + " The commands have been messaged to you", event.getMessage().getChannel());

		} catch (DiscordException | RateLimitException e) {
			logger.error(e);
		}
	}
	
	@EventSubscriber
	public void onCreatePoll(CreatePollEvent event){
		// https://strawpoll.me/api/v2/polls
		Utils.createPoll(event);
	}
	
	@EventSubscriber
	public void onGetPoll(GetPollEvent event){
		logger.warn("in event listener");
		Utils.getPoll(event.getMessage().getContent());
	}
	
	
	
	
	/**
	 * Posts the commands in the channel requested
	 * @param event
	 */
//	@EventSubscriber
//	public void onCommands(CommandsEvent event){
//		//Utils.writeMessageToChannel(event.getMessage().getAuthor().mention() + BotConstants.HELP_MESSAGE, event.getMessage().getChannel());
////		IPrivateChannel channel = Authorization.client.getOrCreatePMChannel(event.getMessage().getAuthor());
////		Utils.writeMessageToChannel("test", channel);
//		try {
//			IPrivateChannel channel = Authorization.client.getOrCreatePMChannel(event.getMessage().getAuthor());
//			Utils.writeMessageToChannel(BotConstants.HELP_MESSAGE + BotConstants.HELP_MESSAGE, channel);
//
//		} catch (DiscordException | RateLimitException e) {
//			logger.error(e);
//		}
//	}

//	/**
//	 * Handles the giveaways
//	 * TODO
//	 * @param event
//	 */
//	@EventSubscriber
//	public void onGiveaway(GiveawayEvent event){
//
//		Utils.writeMessageToChannel(BotConstants.UNFINISHED, event.getMessage().getChannel());
//	}


}

