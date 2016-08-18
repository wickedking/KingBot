package eventListeners;

import events.BanEvent;
import events.CommandsEvent;
import events.GiveawayEvent;
import events.HelloEvent;
import events.KeywordEvent;
import events.KickEvent;
import events.LevelsEvent;
import events.PruneEvent;
import events.RankEvent;
import events.TimeoutEvent;
import login.Authorization;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MessageList;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class ReadyEventTest {

	@EventSubscriber
	public void onReady(ReadyEvent event) {
		System.out.println("ready method");
		Authorization.readyStatus = true;
	}

	@EventSubscriber
	public void messageEventSub(MessageReceivedEvent event){
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

		} 

	}



	@EventSubscriber
	public void onBan(BanEvent event){

		IGuild guild = event.getClient().getGuilds().get(0);
		try {
			Authorization.client.getGuildByID(guild.getID()).banUser(event.getMessage().getMentions().get(0));
		} catch (RateLimitException | MissingPermissionsException | DiscordException e) {
			e.printStackTrace();
		}

	}

	@EventSubscriber
	public void onKick(KickEvent event){
//		try {
			try {
				Authorization.client.getGuilds().get(0).kickUser(event.getMessage().getMentions().get(0));
			} catch (RateLimitException | MissingPermissionsException | DiscordException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	@EventSubscriber
	public void onCommands(CommandsEvent event){
		try {
			String commands = "````ban - Bans the person tagged \n `commands - Lists the commands \n `giveaway - Starts a giveaway, i think. idk yet \n `keyword - Lists the current keyword filters \n `kick - Kicks the person from the server \n `levels - Shows a link to the website with all the levels \n `prune - Deletes the last 'X' messages \n `rank - Shows your current rank \n `timeout - Removes the ability to type or talk from the person```";
			new MessageBuilder(Authorization.client).withChannel(event.getMessage().getChannel()).withContent(event.getMessage().getAuthor().mention() + commands).build();
		} catch (RateLimitException | DiscordException | MissingPermissionsException e) {
			e.printStackTrace();
		}
	}

	@EventSubscriber
	public void onGiveaway(GiveawayEvent event){
		try {
			new MessageBuilder(Authorization.client).withChannel(event.getMessage().getChannel()).withContent("This feature is not yet implemented. Please tell WickedKing to get off his lazy ass and finish it").build();
		} catch (RateLimitException | DiscordException | MissingPermissionsException e) {
			e.printStackTrace();
		}
	}

	@EventSubscriber
	public void onTimeout(TimeoutEvent event){
		try {
			new MessageBuilder(Authorization.client).withChannel(event.getMessage().getChannel()).withContent("This feature is not yet implemented. Please tell WickedKing to get off his lazy ass and finish it").build();
		} catch (RateLimitException | DiscordException | MissingPermissionsException e) {
			e.printStackTrace();
		}
	}

	@EventSubscriber
	public void onKeyword(KeywordEvent event){
		try {
			new MessageBuilder(Authorization.client).withChannel(event.getMessage().getChannel()).withContent("This feature is not yet implemented. Please tell WickedKing to get off his lazy ass and finish it").build();
		} catch (RateLimitException | DiscordException | MissingPermissionsException e) {
			e.printStackTrace();
		}
	}

	@EventSubscriber
	public void onPrune(PruneEvent event){

		MessageList list = event.getMessage().getChannel().getMessages();
		String totalMessage = event.getMessage().getContent();
		int index = totalMessage.indexOf(" ");
		int numMessages = Integer.parseInt(totalMessage.substring(index).trim());
		try {
			list.get(0).delete();
			for(int i = 0; i < numMessages; i++){
				IMessage message = list.get(0);
				message.delete();

			}
		} catch (RateLimitException | MissingPermissionsException | DiscordException e) {
			e.printStackTrace();
		}
	
	}

	@EventSubscriber
	public void onRank(RankEvent event){
		try {
			new MessageBuilder(Authorization.client).withChannel(event.getMessage().getChannel()).withContent("This feature is not yet implemented. Please tell WickedKing to get off his lazy ass and finish it").build();
		} catch (RateLimitException | DiscordException | MissingPermissionsException e) {
			e.printStackTrace();
		}
	}

	@EventSubscriber
	public void onLevels(LevelsEvent event){
		try {
			new MessageBuilder(Authorization.client).withChannel(event.getMessage().getChannel()).withContent("This feature is not yet implemented. Please tell WickedKing to get off his lazy ass and finish it").build();
		} catch (RateLimitException | DiscordException | MissingPermissionsException e) {
			e.printStackTrace();
		}
	}

	@EventSubscriber
	public void onMessage(HelloEvent event){
		System.out.println(event.getMessage().getAuthor().getName() + " said Hello!");
	}

}


//event.getMessage().getMentions().get(0).
//String message = event.getMessage().getContent();
//System.out.println(message);
//System.out.println(event.getMessage().getMentions().size());
//if(event.getMessage().getMentions().size() <= 0 ){
//	System.out.println("first");
//	Collection<IUser> users = Authorization.client.getUsers();
//	for(IUser user : users){
//		System.out.println(user.getName() + " | " + message.toLowerCase().compareTo(user.getName().toLowerCase()));
//		if (message.toLowerCase().compareTo(user.getName().toLowerCase()) == 0){
//			System.out.println("second");
//			new MessageBuilder(Authorization.client).withChannel(event.getMessage().getChannel()).withContent("You meant " + user.mention() + "right?").build();
//		}
//	}
//} else {
//	System.out.println("in else message");
//	new MessageBuilder(Authorization.client).withChannel(event.getMessage().getChannel()).withContent("This feature is not yet implemented. Please tell WickedKing to get off his lazy ass and finish it").build();
//}
//} catch (RateLimitException | DiscordException | MissingPermissionsException e) {
//e.printStackTrace();
//}
