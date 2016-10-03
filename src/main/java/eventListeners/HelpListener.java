package eventListeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import constants.BotConstants;
import events.HelpEvent;
import sx.blah.discord.api.events.EventSubscriber;
import util.Utils;

/**
 * Listener for when a user asks for help with a command
 * @author King
 *
 */
public class HelpListener {
	
	private static final Logger logger = LogManager.getLogger(HelpListener.class);

	/**
	 * When a user asks for help with a command, will display a helpful message
	 * @param event
	 */
	@EventSubscriber
	public void onHelp(HelpEvent event){
		
		String message = event.getMessage().getContent();
		logger.warn("Help event: " + message);
		message = message.substring("`help ".length());
		logger.warn("Help event2: " + message);
		String output = "";
		if(message.startsWith("prune")){
			output = BotConstants.HELP_PRUNE ;
			
		} else if (message.startsWith("rank")){
			output = BotConstants.HELP_RANK ;

		} else if (message.startsWith("levels")){
			output = BotConstants.HELP_LEVEL ;

		} else if (message.startsWith("keywords")) {
			output = BotConstants.HELP_KEYWORDS ;

		} else if (message.startsWith("timeout ")) {
			output = BotConstants.HELP_TIMEOUT ;

		} else if (message.startsWith("giveaway")) {
			output = BotConstants.HELP_GIVEAWAY ;

		} else if (message.startsWith("commands")) {
			output = BotConstants.HELP_COMMANDS ;

		} else if (message.startsWith("kick ")) {
			output = BotConstants.HELP_KICK ;

		} else if (message.startsWith("ban ")) {
			output = BotConstants.HELP_BAN ;

		} else if (message.startsWith("serverinfo")) {
			output = BotConstants.HELP_SERVERINFO ;

		} else if (message.startsWith("setkeyword ")) {
			output = BotConstants.HELP_SETKEYWORD ;

		} else if (message.startsWith("deletekeyword ")) {
			output = BotConstants.HELP_DELETEKEYWORD ;

		} else if (message.startsWith("setlogging")) {
			output = BotConstants.HELP_SETLOGGING ;

		} else if (message.startsWith("deletelogging")) {
			output = BotConstants.HELP_DELETELOGGING ;

		} else if (message.startsWith("getlogging")) {
			output = BotConstants.HELP_GETLOGGING ;

		} else if (message.startsWith("playmusic")) {
			output = BotConstants.HELP_PLAYMUSIC ;
			
		}  else if (message.startsWith("`about")) {
			output = BotConstants.HELP_ABOUT ;

		}
		
		Utils.writeMessageToChannel(output, event.getMessage().getChannel());

	}
}
