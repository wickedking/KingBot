package eventListeners;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import constants.BotConstants;
import events.AboutEvent;
import events.AdminCommandsEvent;
import events.AdviceEvent;
import events.AudioJoinEvent;
import events.AudioPlayEvent;
import events.AudioStopEvent;
import events.BanEvent;
import events.CommandsEvent;
import events.CreatePollEvent;
import events.EightBallEvent;
import events.GetLoggingEvent;
import events.GetPollEvent;
import events.GetServerInfoEvent;
import events.HelpEvent;
import events.InsultEvent;
import events.JokeEvent;
import events.KickEvent;
import events.LeaveChannelEvent;
import events.LennyEvent;
import events.MiddleFingerEvent;
import events.PlayMusicEvent;
import events.PrivateMessageEvent;
import events.PruneEvent;
import events.ShrugEvent;
import events.TableFlipEvent;
import events.TimeoutEvent;
import events.UnTableFlipEvent;
import login.Authorization;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IPrivateChannel;
import sx.blah.discord.handle.obj.IRegion;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.VerificationLevel;
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
	
	private static AudioPlayer AUDIO_PLAYER = null;

	/**
	 * MessageReceivedEvent listener method, parses message and fires new event if needed
	 * Really need to refactor
	 * @param event
	 */
	@EventSubscriber
	public void onMessageReceived(MessageReceivedEvent event){
		logger.warn(event.getMessage().getAuthor().getID());
		LocalTime time = LocalTime.now();
		logger.warn(time.getHour());

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

//		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "setlogging")) {
//			event.getClient().getDispatcher().dispatch(new SetLoggingEvent(event.getMessage()));

		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "getlogging")) {
			event.getClient().getDispatcher().dispatch(new GetLoggingEvent(event.getMessage()));

//		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "playmusic")) {
//			event.getClient().getDispatcher().dispatch(new PlayMusicEvent(event.getMessage()));

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
			
		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "join")) {
			event.getClient().getDispatcher().dispatch(new AudioJoinEvent(event.getMessage()));
			
		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "playmusic")) {
			event.getClient().getDispatcher().dispatch(new AudioPlayEvent(event.getMessage()));
			
		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "stopmusic")) {
			event.getClient().getDispatcher().dispatch(new AudioStopEvent(event.getMessage()));
			
		} else if (event.getMessage().getContent().startsWith(BotConstants.BOT_PREFIX + "leavechannel")) {
			event.getClient().getDispatcher().dispatch(new LeaveChannelEvent(event.getMessage()));
			
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
	 * Plays music
	 * TODO fix
	 * @param event
	 */
//	@EventSubscriber
//	public void onPlayMusic(PlayMusicEvent event){
//		AudioPlayer player = AudioPlayer.getAudioPlayerForGuild(event.getMessage().getGuild());
//		try {
//			player.queue(new URL("https://www.youtube.com/watch?v=308KpFZ4cT8"));
//			logger.warn("Playing music maybe");
//		} catch (IOException | UnsupportedAudioFileException e) {
//			logger.error(e);
//		}

//	}

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
	
	/**
	 * Create a poll on strawpoll.me
	 * @param event
	 */
	@EventSubscriber
	public void onCreatePoll(CreatePollEvent event){
		// https://strawpoll.me/api/v2/polls
		Utils.createPoll(event);
	}
	
	/**
	 * Used to return the poll link
	 * @param event
	 */
	@EventSubscriber
	public void onGetPoll(GetPollEvent event){
		logger.warn("in event listener");
		Utils.getPoll(event.getMessage().getContent());
	}
	
	/**
	 * Get basic info about the server
	 * @param event
	 */
	@EventSubscriber
	public void onGetServerInfo(GetServerInfoEvent event){
		IGuild guild = event.getMessage().getGuild();
		LocalDateTime creationTime = guild.getCreationDate();
		String icon = guild.getIconURL();
		String guildName = guild.getName();
		IUser owner = guild.getOwner();
		IRegion region = guild.getRegion();
		int totalUsers = guild.getTotalMemberCount();
		VerificationLevel verificationLevel = guild.getVerificationLevel();
		int voiceChannelNumber = guild.getVoiceChannels().size();
		int numberChannels = guild.getChannels().size();
		int numberWebhooks = guild.getWebhooks().size();
		int emojis = guild.getEmojis().size();
		
		Utils.writeMessageToChannel("```Creation date: " + creationTime + "\n Icon: " + icon + "\n GuildName: " + guildName + "\n Owner: " + owner.getName() + "\n Region: " 
		+ region + "\n Total Users: " + totalUsers + "\n Verification level: " + verificationLevel + "\n WebHooks: " + numberWebhooks 
		+ "\n Number of Text channels: " + numberChannels + "\n Number voice channels: " + voiceChannelNumber + "\n Number of custom Emojis " + emojis + "```", event.getMessage().getChannel());
	}
	
	/**
	 * used to write lenny face
	 * @param event
	 */
	@EventSubscriber
	public void onLenny(LennyEvent event){
		Utils.writeMessageToChannel("( ͡° ͜ʖ ͡°)", event.getMessage().getChannel());
	}

	/**
	 * Used to write middle finger ascii to channel
	 * @param event
	 */
	@EventSubscriber
	public void onMiddleFinger(MiddleFingerEvent event){
		Utils.writeMessageToChannel("╭∩╮(Ο_Ο)╭∩╮", event.getMessage().getChannel());
	}
	
	/**
	 * Used to join the audio channel
	 * @param event
	 */
	@EventSubscriber
	public void onAudioJoin(AudioJoinEvent event){
		AUDIO_PLAYER = AudioPlayer.getAudioPlayerForGuild(event.getMessage().getGuild());
		try {
			event.getClient().getVoiceChannels().get(0).join();
		} catch (MissingPermissionsException e) {
			e.printStackTrace();
		}
		
		//190868309313323008
	}
	
	@EventSubscriber
	public void onAudioPlayer(AudioPlayEvent event){
		System.out.println("in audio player method");
		try {
			AUDIO_PLAYER.queue(new File("C:\\Users\\King\\Desktop\\Epic Sax Guy Loop.mp3"));
			//SUPER LAGGY PLEASE FIX
			//AUDIO_PLAYER.queue(new URL("https://youtu.be/kxopViU98Xo"));
			
		} catch (IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}
	
	@EventSubscriber
	public void onAudioStop(AudioStopEvent event){
		System.out.println("in audio stop method");
		AUDIO_PLAYER.togglePause();
	}
	
	@EventSubscriber
	public void onLeaveChannel(LeaveChannelEvent event) {
		System.out.println("in audio leaving method");
		event.getClient().getConnectedVoiceChannels().get(0).leave();
	}
	
}

