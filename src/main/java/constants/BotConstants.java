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
	
	public static final String HELP_MESSAGE = "``` `ban - (admin only) Bans the person tagged \n `commands - Lists the commands \n `giveaway - (admin only) Starts a giveaway, i think. idk yet \n `keywords - Lists the current keyword filters \n `deletekeyword - (admin only) Deletes the keyword specified \n `setkeyword - (admin only) Setups a new keyword filter, use `help setkeyword for specific information on how to use this command \n `kick - (admin only) Kicks the person from the server \n `levels - Shows a link to the website with all the levels \n `prune - (admin only) Deletes the last 'X' messages \n `rank - Shows your current rank \n `timeout - (admin only) Removes the ability to type or talk from the person \n `serverinfo - Shows basic info about the server \n `help - use to find more specific info on each command \n `setlogging - (admin only) Sets the logging channel to this channel \n `deletelogging - (admin only) Removes the logging from this channel \n `getlogging - (admin only) yeah just dont use this \n `playmusic - a work in progress \n `help - pass in a command from above and it will give you more details on its use```";

	public static final String GUILD_ID = "T100_guildId";
	
	public static final String NOT_AUTHORIZED = "Not Authorized to use this command";
	
	public static final String ABOUT_MESSAGE = "This bot was made with love by WickedKing. If have you suggestions of new features or things that need fixing, please contact him. While his name is wicked, he wont bite. He accepts no responsiblity of anything that happens while using his bot. Enjoy";
		
	public static final String HELP_PRUNE = "``` `prune \n Used to delete x amount of messages in the channel its used it. Will auto delete the prune command.\n \n Example: `prune 10 - will delete the 10 previous messages \n Note: for large values this may take a little bit of time due to discord rate limiting. ```";
	public static final String HELP_RANK = "''' `rank \n Used to show your current ranking and xp for the server. \n \n Example: `rank ```";
	public static final String HELP_LEVEL = "``` `level \n Used to show all the levels for all users. \n \n Example: `level ```";
	public static final String HELP_KEYWORDS = "``` `keywords \n Used to show all current keyword filters setup for the server. keywords can be set to be auto deleted, or auto notify certain users. Use `help setkeyword for more info. \n \n Example: `Keywords '''";
	public static final String HELP_TIMEOUT = "``` `timeout @user \n Places mentioned user in a timeout role. Timeout will remove the ability to send messages in any channel in the server. Must remember to `untimeout @user. \n\n Example: `timeout @WickedKing - would place WickedKing in timeout (how ruud) ```";
	public static final String HELP_GIVEAWAY = "``` `giveaway \n Would start a giveaway type thing. This will be implemented later. Just forget about this command for now ```";
	public static final String HELP_COMMANDS = "``` `commands \n Will show all the commands that the bot currently accepts with basic info about them. \n \n Example: `commands ```";
	public static final String HELP_KICK = "``` `kick @user \n Will kick the user mentioned from the server. Will kick with no warning. Use with caution. \n \n Example: `kick @WickedKing - Would kick WickedKing from the server. (That makes me sad) ```";
	public static final String HELP_BAN = "``` `ban @user \n Will ban the user mentioned from the server. Will ban with no warning. Use with caution. \n \n Example: `ban @WickedKing - Would ban WickedKing from the server. (That makes me really sad. I liked him) ```";
	public static final String HELP_SERVERINFO = "``` `serverinfo \n Will show basic stats about the server. \n \n Example: `serverinfo ```";
	public static final String HELP_SETKEYWORD = "``` `setkeyword 'keyword' 'action1' action2' 'message' \n Will setup the keyword filter for the server. 'keyword' is the word that is flagged. 'action1' is either 'keep' or 'delete'. Action2 is 'standalone' or 'included'. 'message' is the message that is posted when the filter is tripped, put 'none' if you dont want a message to be posted \n \n Example `setkeyword boss delete standalone please dont call me that - Will create a keyword filter on 'boss' as long as the word is not included in another word(standalone) and will post the rest of the message to chat ```";
	public static final String HELP_DELETEKEYWORD = "``` `deletekeyword 'keyword' \n Will delete the keyword filter associated. \n \n Example: `deletekeyword boss - Will delete the keyword of 'boss' ```";
	public static final String HELP_SETLOGGING = "``` `setlogging \n This will set the logging channel to the current channel. Logging will include things like: users banned, kicked, messages deleted, avatars changed, names changes, etc. \n \n Example `setlogging ```";
	public static final String HELP_DELETELOGGING = "``` `deletelogging \n This will delete the logging to this channel for this server. Must be used in the channel with the current logging associated. \n \n Example: `deletelogging ```";
	public static final String HELP_GETLOGGING = "``` `getlogging \n Yeah this is here for reason. But you dont need to use this command. Just forgot this command is here. ```";
	public static final String HELP_PLAYMUSIC = "``` `playmusic \n Will be used in the future once WickedKing finishes the code to make this work. For now just sit back and wish for this feature, and maybe it will come true ```";
	public static final String HELP_ABOUT = "``` `about \n Displays a short about message for this bot. \n \n Example: `about ```";
		

	public static final String BALL1 = "It is certain";
	public static final String BALL2 = "It is decidedly so";
	public static final String BALL3 = "Without a doubt";
	public static final String BALL4 = "Yes, definitely";
	public static final String BALL5 = "You may rely on it";
	public static final String BALL6 = "As I see it, yes";
	public static final String BALL7 = "Most likely";
	public static final String BALL8 = "Outlook good";
	public static final String BALL9 = "Yes";
	public static final String BALL10 = "Signs point to yes";
	public static final String BALL11 = "Reply hazy try again";
	public static final String BALL12 = "Ask again later";
	public static final String BALL13 = "Better not tell you now";
	public static final String BALL14 = "Cannot predict now";
	public static final String BALL15 = "Concentrate and ask again";
	public static final String BALL16 = "Don't count on it";
	public static final String BALL17 = "My reply is no";
	public static final String BALL18 = "My sources say no";
    public static final String BALL19 = "Outlook not so good";
	public static final String BALL20 = "Very doubtful";
	
	public static final String BOT_PREFIX = "`";
	
	public static final String SHRUG = "¯\\_(ツ)_/¯";
	public static final String TABLE_FLIP = "(╯°□°）╯︵ ┻━┻";
	public static final String UN_TABLE_FLIP = "┬─┬﻿ ノ( ゜-゜ノ)";
	
	public static final String UNFINISHED = "This feature is not yet implemented. Please tell WickedKing to get off his lazy ass and finish it";
	
	public static final String BAD_KEYWORD = "That keyword command is not correct. Please correct and try again";
	public static final String DELETE_KEYWORD_UNSUCCESSFUL = "Your keyword attempt was not deleted successfully";
	public static final String DELETE_LOGGING_UNSUCCESSFUL = "Error occured when stopping logging. Please make sure your in the logging channel when using this command";
	
	
	
	
	
	/**
	 * 
	 */
	private BotConstants(){
		//Private constructor to hide implementation
	}

}
