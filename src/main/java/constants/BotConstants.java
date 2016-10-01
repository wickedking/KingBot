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
	
	public static final String HELP_MESSAGE = "``` `ban - Bans the person tagged \n `commands - Lists the commands \n `giveaway - Starts a giveaway, i think. idk yet \n `keywords - Lists the current keyword filters \n `deletekeyword - Deletes the keyword specified \n `setkeyword - Setups a new keyword filter, use `help setkeyword for specific information on how to use this command \n `kick - Kicks the person from the server \n `levels - Shows a link to the website with all the levels \n `prune - Deletes the last 'X' messages \n `rank - Shows your current rank \n `timeout - Removes the ability to type or talk from the person \n `serverinfo - Shows basic info about the server \n `help - use to find more specific info on each command \n `setlogging - Sets the logging channel to this channel \n `deletelogging - Removes the logging from this channel \n `getlogging - yeah just dont use this \n `playmusic - a work in progress \n `help - pass in a command from above and it will give you more details on its use```";

	public static final String GUILD_ID = "T100_guildId";
	
	public static final String NOT_AUTHORIZED = "Not Authorized to use this command";
	
	public static final String ABOUT_MESSAGE = "This bot was made with love by WickedKing. If have suggestions of new features or things that need fixing, please contact him. While his name is wicked, he wont bite. He accepts no responsiblity of anything that happens while using his bot. Enjoy";
	
	private BotConstants(){
		//Private constructor to hide implementation
	}

}
