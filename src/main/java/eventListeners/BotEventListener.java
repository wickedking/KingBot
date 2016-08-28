package eventListeners;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Keyword;
import dao.BotDAO;
import details.ServerInfo;
import events.BanEvent;
import events.CommandsEvent;
import events.DeleteKeywordEvent;
import events.GiveawayEvent;
import events.KeywordCheckEvent;
import events.KeywordEvent;
import events.KickEvent;
import events.LevelsEvent;
import events.PruneEvent;
import events.RankEvent;
import events.ServerInfoEvent;
import events.SetKeywordEvent;
import events.TimeoutEvent;
import login.Authorization;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageList;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;
import util.Utils;

/**
 * Holds all the event listeners in the methods. 
 * @author WickedKing
 *
 */
public class BotEventListener {
	
	//@Autowired
	private BotDAO botDAO = new BotDAO();
	
	Map<String, ServerInfo> servers = new HashMap<String, ServerInfo>();

	/**
	 * ReadyEvent listener method
	 * @param event
	 */
	@EventSubscriber
	public void onReady(ReadyEvent event) {
		System.out.println("ready method");
		Authorization.readyStatus = true;
		setup();
	}
	
	/**
	 * setups the data needed on construction
	 */
	private void setup(){
		servers = botDAO.getServersInfo();
	}
	
	/**
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
		
		event.getClient().getDispatcher().dispatch(new KeywordCheckEvent(event.getMessage()));
		
		//TODO refactor, please just refactor this
		if(event.getMessage().getContent().startsWith("`prune")){
			event.getClient().getDispatcher().dispatch(new PruneEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith("`rank")){
			event.getClient().getDispatcher().dispatch(new RankEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith("`levels")){
			event.getClient().getDispatcher().dispatch(new LevelsEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith("`keywords")) {
			event.getClient().getDispatcher().dispatch(new KeywordEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith("`timeout")) {
			event.getClient().getDispatcher().dispatch(new TimeoutEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith("`giveaway")) {
			event.getClient().getDispatcher().dispatch(new GiveawayEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith("`commands")) {
			event.getClient().getDispatcher().dispatch(new CommandsEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith("`kick")) {
			event.getClient().getDispatcher().dispatch(new KickEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith("`ban")) {
			event.getClient().getDispatcher().dispatch(new BanEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith("`serverinfo")) {
			event.getClient().getDispatcher().dispatch(new ServerInfoEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith("`setkeyword")) {
			event.getClient().getDispatcher().dispatch(new SetKeywordEvent(event.getMessage()));
			
		} else if (event.getMessage().getContent().startsWith("`deletekeyword")) {
			event.getClient().getDispatcher().dispatch(new DeleteKeywordEvent(event.getMessage()));
		} 

	}


	/**
	 * Bans the user mentioned
	 * @param event
	 */
	@EventSubscriber
	public void onBan(BanEvent event){
		boolean isAdmin = Utils.isBotAdmin(event.getMessage().getAuthor().getRolesForGuild(event.getMessage().getGuild()));
		if(isAdmin){
			IGuild guild = event.getClient().getGuilds().get(0);
			try {
				Authorization.client.getGuildByID(guild.getID()).banUser(event.getMessage().getMentions().get(0));
			} catch (RateLimitException | MissingPermissionsException | DiscordException e) {
				e.printStackTrace();
			}
		} else{
			Utils.WriteMessageToChannel("Not Authorized to use this command", event.getMessage().getChannel());
		}
	}

	/**
	 * Kicks the user mentioned
	 * @param event
	 */
	@EventSubscriber
	public void onKick(KickEvent event){

		boolean isAdmin = Utils.isBotAdmin(event.getMessage().getAuthor().getRolesForGuild(event.getMessage().getGuild()));
		if(isAdmin){
			try {
				Authorization.client.getGuilds().get(0).kickUser(event.getMessage().getMentions().get(0));
			} catch (RateLimitException | MissingPermissionsException | DiscordException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Utils.WriteMessageToChannel("Not Authorized to use this command", event.getMessage().getChannel());
		}


	}

	/**
	 * Posts the commands in the channel requested
	 * @param event
	 */
	@EventSubscriber
	public void onCommands(CommandsEvent event){
		String commands = "````ban - Bans the person tagged \n `commands - Lists the commands \n `giveaway - Starts a giveaway, i think. idk yet \n `keyword - Lists the current keyword filters \n `kick - Kicks the person from the server \n `levels - Shows a link to the website with all the levels \n `prune - Deletes the last 'X' messages \n `rank - Shows your current rank \n `timeout - Removes the ability to type or talk from the person \n `serverinfo - Shows basic info about the server```";
		Utils.WriteMessageToChannel(event.getMessage().getAuthor().mention() + commands, event.getMessage().getChannel());
	}

	/**
	 * Handles the giveaways
	 * TODO
	 * @param event
	 */
	@EventSubscriber
	public void onGiveaway(GiveawayEvent event){
		
		Utils.WriteMessageToChannel("This feature is not yet implemented. Please tell WickedKing to get off his lazy ass and finish it", event.getMessage().getChannel());
	}

	/**
	 * Handles a timeout for the user mentioned
	 * TODO
	 * @param event
	 */
	@EventSubscriber
	public void onTimeout(TimeoutEvent event){
		boolean isAdmin = Utils.isBotAdmin(event.getMessage().getAuthor().getRolesForGuild(event.getMessage().getGuild()));
		if(isAdmin){
			Utils.WriteMessageToChannel("This feature is not yet implemented. Please tell WickedKing to get off his lazy ass and finish it", event.getMessage().getChannel());
		}else {
			Utils.WriteMessageToChannel("Not Authorized to use this command", event.getMessage().getChannel());
		}

	}

	/**
	 * Deletes the requested number of messages from channel requested from
	 * @param event
	 */
	@EventSubscriber
	public void onPrune(PruneEvent event){
		boolean isAdmin = Utils.isBotAdmin(event.getMessage().getAuthor().getRolesForGuild(event.getMessage().getGuild()));
		if(isAdmin){
			MessageList list = event.getMessage().getChannel().getMessages();
			String totalMessage = event.getMessage().getContent();
			int index = totalMessage.indexOf(" ");
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
				e.printStackTrace();
			}
		} else{
			Utils.WriteMessageToChannel("Not Authorized to use this command", event.getMessage().getChannel());
		}


	}

	/**
	 * Displays the rank of the user mentioned
	 * @param event
	 */
	@EventSubscriber
	public void onRank(RankEvent event){
		Utils.WriteMessageToChannel("This feature is not yet implemented. Please tell WickedKing to get off his lazy ass and finish it", event.getMessage().getChannel());
	}

	/**
	 * Displays the levels of all the people that have been logged.
	 * @param event
	 */
	@EventSubscriber
	public void onLevels(LevelsEvent event){
		Utils.WriteMessageToChannel("This feature is not yet implemented. Please tell WickedKing to get off his lazy ass and finish it", Authorization.client.getChannelByID(event.getMessage().getChannel().getID()));
	}

	/**
	 * Displays current Infomation about the server
	 * @param event
	 */
	@EventSubscriber
	public void onServerInfo(ServerInfoEvent event){
		IGuild server = Authorization.client.getGuilds().get(0);
		Utils.WriteMessageToChannel("```Server Name: " + server.getName() + "\n Owner: " + server.getOwner().getName() + "\n Number of Channels: " + server.getChannels().size() + "\n Server creation date: " + server.getCreationDate() + "\n Number of Roles: " + server.getRoles().size() + "\n Number of users: " + server.getUsers().size() + "\n Number of Voice Channels: " + server.getVoiceChannels().size() + "```", Authorization.client.getChannelByID(event.getMessage().getChannel().getID()));

	}
	
	/**
	 * Posts the keywords that have been setup for a filter
	 * @param event
	 */
	@EventSubscriber
	public void onGetKeywords(KeywordEvent event){
		
		//TODO refactor to replace db call with call to Servers
		boolean isAdmin = Utils.isBotAdmin(event.getMessage().getAuthor().getRolesForGuild(event.getMessage().getGuild()));
		if(isAdmin){
			List<Keyword> keywords = botDAO.getKeywords(event.getMessage().getGuild().getID());
			StringBuilder sb = new StringBuilder();
			for(Keyword keyword : keywords){
				sb.append("**Keyword:** " + keyword.getKeyword());
				sb.append(" | **Action1:** " + keyword.getAction1());
				sb.append(" | **Action2:** " + keyword.getAction2()); 
				sb.append(" | **Message:** " + keyword.getMessage());
				sb.append(" \n");
				
			}
			Utils.WriteMessageToChannel(sb.toString(), event.getMessage().getChannel());
		} else {
			Utils.WriteMessageToChannel("Not Authorized to use this command", event.getMessage().getChannel());
		}
		
	}

	/**
	 * Adds the keyword that is specified
	 * @param event
	 */
	@EventSubscriber
	public void onSetKeyword(SetKeywordEvent event){
		boolean isAdmin = Utils.isBotAdmin(event.getMessage().getAuthor().getRolesForGuild(event.getMessage().getGuild()));
		if(isAdmin){
			String[] words =  event.getMessage().getContent().split("\\|");
			if(words.length != 5){
				Utils.WriteMessageToChannel("That keyword command is not correct. Please correct and try again", Authorization.client.getChannelByID(event.getMessage().getChannel().getID()));
				return;
			}
			Keyword keyword = new Keyword();
			keyword.setGuildId(event.getMessage().getGuild().getID());
			keyword.setKeyword(words[1].trim());
			keyword.setAction1(words[2].trim());
			keyword.setAction2(words[3].trim());
			keyword.setMessage(words[4].trim());
			boolean success =  botDAO.saveKeyword(keyword);
			if(success){
				Utils.WriteMessageToChannel("Keyword: " + keyword.getKeyword() + " was saved successfully", Authorization.client.getChannelByID(event.getMessage().getChannel().getID()));
				resetServerInfo(event.getMessage().getGuild().getID());
			} else {
				Utils.WriteMessageToChannel("Your keyword attempt was not saved successfully", Authorization.client.getChannelByID(event.getMessage().getChannel().getID()));
			}
		} else{
			Utils.WriteMessageToChannel("Not Authorized to use this command", event.getMessage().getChannel());
		}
		
		
	}
	
	/**
	 * Deletes the specified keyword
	 * @param event
	 */
	@EventSubscriber
	public void onDeleteKeyword(DeleteKeywordEvent event){
		boolean isAdmin = Utils.isBotAdmin(event.getMessage().getAuthor().getRolesForGuild(event.getMessage().getGuild()));
		if(isAdmin){
			Keyword keyword = new Keyword();
			keyword.setGuildId(event.getMessage().getGuild().getID());
			keyword.setKeyword(event.getMessage().getContent().replace("`deletekeyword", " ").trim());
			boolean success = botDAO.deleteKeyword(keyword);
			if(success){
				Utils.WriteMessageToChannel("Keyword: " + keyword.getKeyword() + " was deleted successfully", Authorization.client.getChannelByID(event.getMessage().getChannel().getID()));
				resetServerInfo(event.getMessage().getGuild().getID());
			} else {
				Utils.WriteMessageToChannel("Your keyword attempt was not deleted successfully", Authorization.client.getChannelByID(event.getMessage().getChannel().getID()));
			}
		} else{
			Utils.WriteMessageToChannel("Not Authorized to use this command", event.getMessage().getChannel());
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
				Utils.WriteMessageToChannel("The Keyword: " + keyword.getKeyword() + " was found in the message", Authorization.client.getChannelByID(event.getMessage().getChannel().getID()));
				return;
			}
		}
	}


}

