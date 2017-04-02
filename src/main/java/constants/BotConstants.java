package constants;

/**
 * Constants class
 * @author King
 *
 */
public class BotConstants {
	
	
	
	public static final String NOTHING = "nothing";
	public static final String KEEP = "keep";
	public static final String DELETE = "delete";
	public static final String STANDALONE = "standalone";
	public static final String INCLUDED = "included";
	
	public static final String HELP_MESSAGE = "``` `commands - Lists the commands \n `serverinfo - Shows basic info about the server \n `help - use to find more specific info on each command \n `playmusic - a work in progress \n `joke - Will return a random joke \n `advice - will return some less than helpful advice \n `insult - will give a nice insult \n `8ball - will return a random 8 ball message```";
	public static final String ADMIN_HELP_MESSAGE = "``` `ban - (admin only) Bans the person tagged \n `timeout - (admin only) Removes the ability to type or talk from the person \n `kick - (admin only) Kicks the person from the server \n `prune - (admin only) Deletes the last 'X' messages```";
	
	public static final String NOT_AUTHORIZED = "Not Authorized to use this command";
	public static final String ABOUT_MESSAGE = "This bot was made with love by WickedKing. If have you suggestions of new features or things that need fixing, please contact him. While his name is wicked, he wont bite. He accepts no responsiblity of anything that happens while using his bot. Enjoy";
		
	public static final String HELP_PRUNE = "``` `prune \n Used to delete x amount of messages in the channel its used it. Will auto delete the prune command.\n \n Example: `prune 10 - will delete the 10 previous messages \n Note: for large values this may take a little bit of time due to discord rate limiting. ```";
	public static final String HELP_RANK = "''' `rank \n Used to show your current ranking and xp for the server. \n \n Example: `rank ```";
	public static final String HELP_TIMEOUT = "``` `timeout @user \n Places mentioned user in a timeout role. Timeout will remove the ability to send messages in any channel in the server. Must remember to `untimeout @user. \n\n Example: `timeout @WickedKing - would place WickedKing in timeout (how ruud) ```";
	public static final String HELP_COMMANDS = "``` `commands \n Will show all the commands that the bot currently accepts with basic info about them. \n \n Example: `commands ```";
	public static final String HELP_KICK = "``` `kick @user \n Will kick the user mentioned from the server. Will kick with no warning. Use with caution. \n \n Example: `kick @WickedKing - Would kick WickedKing from the server. (That makes me sad) ```";
	public static final String HELP_BAN = "``` `ban @user \n Will ban the user mentioned from the server. Will ban with no warning. Use with caution. \n \n Example: `ban @WickedKing - Would ban WickedKing from the server. (That makes me really sad. I liked him) ```";
	public static final String HELP_SERVERINFO = "``` `serverinfo \n Will show basic stats about the server. \n \n Example: `serverinfo ```";
	public static final String HELP_PLAYMUSIC = "``` `playmusic \n Will be used in the future once WickedKing finishes the code to make this work. For now just sit back and wish for this feature, and maybe it will come true ```";
	public static final String HELP_ABOUT = "``` `about \n Displays a short about message for this bot. \n \n Example: `about ```";
	
	public static final String BOT_PREFIX = "`";
	
	public static final String SHRUG = "¯\\_(ツ)_/¯";
	public static final String TABLE_FLIP = "(╯°□°）╯︵ ┻━┻";
	public static final String UN_TABLE_FLIP = "┬─┬﻿ ノ( ゜-゜ノ)";
	
	public static final String UNFINISHED = "This feature is not yet implemented. Please tell WickedKing to get off his lazy ass and finish it";
	
	public static final String POLL_URL = "https://strawpoll.me/api/v2/";
	
	
	/**
	 * 
	 */
	private BotConstants(){
		//Private constructor to hide implementation
	}

}
