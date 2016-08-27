package eventListeners;

import java.util.List;

import bean.Keywords;
import dao.BotDAO;
import events.BanEvent;
import events.CommandsEvent;
import events.GiveawayEvent;
import events.KeywordEvent;
import events.KickEvent;
import events.LevelsEvent;
import events.PruneEvent;
import events.RankEvent;
import events.ServerInfoEvent;
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

	/**
	 * ReadyEvent listener method
	 * @param event
	 */
	@EventSubscriber
	public void onReady(ReadyEvent event) {
		System.out.println("ready method");
		Authorization.readyStatus = true;
	}

	/**
	 * MessageReceivedEvent listener method, parses message and fires new event if needed
	 * Really need to refactor
	 * @param event
	 */
	@EventSubscriber
	public void onMessageReceived(MessageReceivedEvent event){
		if(event.getMessage().getContent().startsWith("`prune")){
			event.getClient().getDispatcher().dispatch(new PruneEvent(event.getMessage()));
			System.out.println("pruneEvent was fired");

		} else if (event.getMessage().getContent().startsWith("`rank")){
			event.getClient().getDispatcher().dispatch(new RankEvent(event.getMessage()));
			System.out.println("rankEvent was fired");

		} else if (event.getMessage().getContent().startsWith("`levels")){
			event.getClient().getDispatcher().dispatch(new LevelsEvent(event.getMessage()));
			System.out.println("levelsEvent was fired");

		} else if (event.getMessage().getContent().startsWith("`keywords")) {
			event.getClient().getDispatcher().dispatch(new KeywordEvent(event.getMessage()));
			System.out.println("keywordsEvent was fired");

		} else if (event.getMessage().getContent().startsWith("`timeout")) {
			event.getClient().getDispatcher().dispatch(new TimeoutEvent(event.getMessage()));
			System.out.println("timeoutEvent was fired");

		} else if (event.getMessage().getContent().startsWith("`giveaway")) {
			event.getClient().getDispatcher().dispatch(new GiveawayEvent(event.getMessage()));
			System.out.println("giveawayEvent was fired");

		} else if (event.getMessage().getContent().startsWith("`commands")) {
			event.getClient().getDispatcher().dispatch(new CommandsEvent(event.getMessage()));
			System.out.println("commandsEvent was fired");

		} else if (event.getMessage().getContent().startsWith("`kick")) {
			event.getClient().getDispatcher().dispatch(new KickEvent(event.getMessage()));
			System.out.println("kickEvent was fired");

		} else if (event.getMessage().getContent().startsWith("`ban")) {
			event.getClient().getDispatcher().dispatch(new BanEvent(event.getMessage()));
			System.out.println("banvent was fired");

		} else if (event.getMessage().getContent().startsWith("`serverinfo")) {
			event.getClient().getDispatcher().dispatch(new ServerInfoEvent(event.getMessage()));
			System.out.println("serverEvent was fired");

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
		System.out.println(event.getMessage().getGuild().getID());
		System.out.println(event.getMessage().getAuthor().getID());
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
	 * Posts the keywords that have been setup for a filter
	 * @param event
	 */
	@EventSubscriber
	public void onKeyword(KeywordEvent event){
		boolean isAdmin = Utils.isBotAdmin(event.getMessage().getAuthor().getRolesForGuild(event.getMessage().getGuild()));
		if(isAdmin){
			List<Keywords> keywords = botDAO.getKeywords(event.getMessage().getGuild().getID());
			for(Keywords keyword : keywords){
				Utils.WriteMessageToChannel(keyword.toString() ,event.getMessage().getChannel());
			}
			//Utils.WriteMessageToChannel("This feature is not yet implemented. Please tell WickedKing to get off his lazy ass and finish it", event.getMessage().getChannel());
		} else {
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
				// TODO Auto-generated catch block
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


}

