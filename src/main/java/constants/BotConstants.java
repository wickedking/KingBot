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
	
	public static final String HELP_MESSAGE = "``` `commands - Lists the commands \n `keywords - Lists the current keyword filters \n `rank - Shows your current rank \n `levels - Shows a link to the website with all the levels \n `serverinfo - Shows basic info about the server \n `help - use to find more specific info on each command \n `playmusic - a work in progress \n `joke - Will return a random joke \n `advice - will return some less than helpful advice \n `insult - will give a nice insult```";

	public static final String ADMIN_HELP_MESSAGE = "``` `ban - (admin only) Bans the person tagged \n `setlogging - (admin only) Sets the logging channel to this channel \n `deletelogging - (admin only) Removes the logging from this channel \n `getlogging - (admin only) yeah just dont use this \n `timeout - (admin only) Removes the ability to type or talk from the person \n `deletekeyword - (admin only) Deletes the keyword specified \n `setkeyword - (admin only) Setups a new keyword filter, use `help setkeyword for specific information on how to use this command \n `kick - (admin only) Kicks the person from the server \n `prune - (admin only) Deletes the last 'X' messages```";
	
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
	
	
	
	public static final String joke1 = "She was only a whiskey-maker, but he loved her still.";
	public static final String joke2 = "Why do chicken coops only have two doors? Because if they had four, they would be chicken sedans!";
	public static final String joke3 = "How do you make holy water? You boil the hell out of it.";
	public static final String joke4 = "What’s Forrest Gump’s password? 1forrest1";
	public static final String joke5 = "I tried to catch some fog, but I mist.";
	public static final String joke6 = "England has no kidney bank, but it does have a Liverpool.";
	public static final String joke7 = "Velcro, what a rip off!";
	public static final String joke8 = "A dyslexic man walks into a bra.";
	public static final String joke9 = "Deja Moo: The feeling that you've heard this bull before.";
	public static final String joke10 = "Two cows are standing next to each other in a field. Daisy says to Dolly, \"I was artificially inseminated this morning.\" \"I don't believe you,\" says Dolly. \"It's true, no bull!\" exclaims Daisy.";
	public static final String joke11 = "Did you hear about the Buddhist who refused Novocain during a root canal? His goal: transcend dental medication.";
	public static final String joke12 = "And finally, there was the person who sent ten different puns to friends, with the hope that at least one of the puns would make them laugh. No pun in ten did.";
	public static final String joke13 = "I'd tell you a chemistry joke but I know I wouldn't get a reaction.";
	public static final String joke14 = "Yesterday I accidentally swallowed some food coloring. The doctor says I'm OK, but I feel like I've dyed a little inside.";
	public static final String joke15 = "Did you hear about the guy who got hit in the head with a can of soda? He was lucky it was a soft drink.";
	public static final String joke16 = "Have you ever tried to eat a clock? It's very time consuming.";
	public static final String joke17 = "I can't believe I got fired from the calendar factory. All I did was take a day off.";
	public static final String joke18 = "When I get naked in the bathroom, the shower usually gets turned on.";
	public static final String joke19 = "I'm emotionally constipated. I haven't given a shit in days.";
	public static final String joke20 = "Why did the scientist install a knocker on his door? He wanted to win the No-bell prize!";
	public static final String joke21 = "Why did the bee get married? Because he found his honey.";
	public static final String joke22 = "I saw an ad for burial plots, and thought to myself this is the last thing I need.";
	public static final String joke23 = "What tea do hockey players drink? Penaltea!";
	public static final String joke24 = "Why did the snowman smile? Because the snowblower is coming.";
	public static final String joke25 = "Two boll weevils grew up in South Carolina. One went to Hollywood and one never amounted to much. The second one, naturally, became known as the lesser of two weevils.";
	
	public static final String insult1 = "May your headphones snag on every door handle";
	public static final String insult2 = "May you press 'A' too hastily and be forced to speak with the nurse at the Pokemon Center all over again";
	public static final String insult3 = "May you forever feel your cell phone vibrating in the pocket it's not even in";
	public static final String insult4 = "May the chocolate chips in your cookies always turn out to be raisins";
	public static final String insult5 = "May your tea be too hot when you receive it, and too cold by the time you remember it's there";
	public static final String insult6 = "May your chair produce a sound similar to a fart, but only once, such that you cannot reporduce it to prove that it was just the chair";
	public static final String insult7 = "May your mother come to talk to you, and then leave your door slightly ajar, so that you have to get up and close it";
	public static final String insult8 = "May the pin of the bathroom stall never reach the lock to close the door";
	public static final String insult9 = "May your spoon always slip and sink under the hot soup you eat";
	public static final String insult10 = "May all your Facebook notifications be game invites";
	public static final String insult11 = "May you have your laptop charge overnight without noticing the chord isn't plugged into the wall";
	public static final String insult12 = "May every \"empty\" parking space you see in the distance actually contain a motorcycle";
	public static final String insult13 = "May every sock you wear be slightly rotated, just enough for it to be uncomfortable";
	public static final String insult14 = "May you never be quite certain as to whether that pressure is a fart or poop";
	public static final String insult15 = "May your return calls always go unanswered even though you literally just fucking called me, Jesse";
	public static final String insult16 = "May your cookie always be slightly too large to fit inside your glass of milk";
	public static final String insult17 = "May both sides of your pillow be warm";
	public static final String insult18 = "May your five year old neighbor have their violin lesson during all of your hangovers";
	public static final String insult19 = "May you always get up from your computer with your headphones still attached";
	public static final String insult20 = "May your article load that extra little bit as your about to click a link so you click an ad instead";
	public static final String insult21 = "May every guitar pick you use fall into the soundhole";
	public static final String insult22 = "May you always step in a wet spot after putting on fresh socks";
	//public static final String insult2 = "";
	
	
	
	public static final String advice = "I swear to drunk I'm not God, but seriously, stay in drugs, eat school, and don't do vegetables.";
	
	public static final String insult = "Your gene pool could use a little chlorine.";
	
	public static final String POLL_URL = "https://strawpoll.me/api/v2/";
	
	
	/**
	 * 
	 */
	private BotConstants(){
		//Private constructor to hide implementation
	}

}
