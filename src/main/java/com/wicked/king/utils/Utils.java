package com.wicked.king.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.wicked.king.KingBotv2Application;
import com.wicked.king.bean.UtilString;
import com.wicked.king.constants.BotConstants;
import com.wicked.king.db.DBAccessorUtils;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RequestBuffer;

/**
 * Utility class to provide common methods for posting messages and other methods
 * @author King
 *
 */
@Component
public class Utils {

	private static DBAccessorUtils repository;

	/**
	 * Logger used for class
	 */
	private static final Logger logger = LogManager.getLogger(Utils.class);

	/**
	 * Role for admin for the bot
	 */
	private static String botAdmin = "Admin";

	/**
	 * Role for a mod for the bot
	 */
	private static String botMod = "Mod";

	/**
	 * Reference to the list of jokes 
	 */
	private static List<String> jokeList;

	/**
	 * Reference to the list of 'insults'
	 */
	private static List<String> insultList;

	/**
	 * Reference to the list of 'advice'
	 */
	private static List<String> adviceList;

	private Utils(){

	}

	/**
	 * Writes specified message to the specified channel
	 * @param message
	 * @param channel
	 */
	public static void writeMessageToChannel(String message, IChannel channel){

		//edit message to remove the actual mentions so logging doesnt mention people
		RequestBuffer.request(() -> {
			MessageBuilder builder = new MessageBuilder(KingBotv2Application.getClient()).withContent(message);
			builder.withChannel(channel);
			try {
				builder.build();
			} catch (DiscordException | MissingPermissionsException e) {
				logger.error(e);
			}

		});

	}

	/**
	 * Will determine if the user has the admin role
	 * @param user 
	 * @param serverOwner 
	 * @param roles
	 * @return
	 */
	public static boolean isBotAdmin(IUser user, IUser serverOwner, List<IRole> roles){
		if(user.equals(serverOwner)){
			return true;
		}
		for(IRole role : roles){
			if(role.getName().equals(botAdmin) || role.getName().equals(botMod)){
				return true;
			}
		}
		return false;
	}

	/**
	 * validation for keywords, action word 1
	 * @param action1
	 * @return
	 */
	public static boolean validateAction1(String action1){
		String[] actions = {BotConstants.KEEP, BotConstants.DELETE};
		for(String action : actions){
			if(action1.trim().equals(action)){
				return true;
			}
		}
		return false;
	}

	/**
	 * validation for keywords, action word 2
	 * @param action2
	 * @return
	 */
	public static boolean validateAction2(String action2){
		String[] actions = {BotConstants.STANDALONE, BotConstants.INCLUDED};
		for(String action : actions){
			if(action2.trim().equals(action)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the list of all possible 8ball answers
	 * @return List of Strings of 8 ball answers
	 */
	public static List<String> getEightBallAnswers(){


		List<UtilString> utilString = repository.findByListType("8ball");
		ArrayList<String> eightBall = new ArrayList<>();
		for(UtilString uString : utilString){
			eightBall.add(uString.getMessage());
		}
		return eightBall;

	}


	/**
	 * Returns a random joke from the list
	 * @param repository2 
	 * @return String of the joke
	 */
	public static String getJoke(){

		Random rand = new Random();
		int index = rand.nextInt(jokeList.size());
		return jokeList.get(index);
	}

	/**
	 * Returns a random insult from the list
	 * @return String of the insult
	 */
	public static String getInsult(){

		Random rand = new Random();
		int index = rand.nextInt(insultList.size());
		return insultList.get(index);
	}

	/**
	 * Returns a random advice from the list
	 * @return String of the advice
	 */
	public static String getAdvice(){
		Random rand = new Random();
		int index = rand.nextInt(adviceList.size());
		return adviceList.get(index);
	}

	/**
	 * Constructs the list of Jokes
	 * @return the list of jokes
	 */
	private static void constructJokeList(){

		List<UtilString> utilString = repository.findByListType("jokes");
		ArrayList<String> jokes = new ArrayList<>();
		for(UtilString uString : utilString){
			jokes.add(uString.getMessage());
		}

		jokeList = jokes;
	}

	/**
	 * Constructs the list of insults
	 * @return the list of insults
	 */
	private static void constructInsultList(){
		List<UtilString> utilString = repository.findByListType("insults");
		ArrayList<String> insults = new ArrayList<>();
		for(UtilString uString : utilString){
			insults.add(uString.getMessage());
		}
		insultList = insults;

	}

	/**
	 * Constructs the list of advice
	 * @return the list of advice
	 */
	private static void constructAdvice() {
		List<UtilString> utilString = repository.findByListType("advice");
		ArrayList<String> advice = new ArrayList<>();
		for(UtilString uString : utilString){
			advice.add(uString.getMessage());
		}
		adviceList = advice;
	}

	/**
	 * 
	 * @param repository2
	 */
	public static void setRepo(DBAccessorUtils repository2) {
		repository = repository2;
		constructJokeList();
		constructInsultList();
		constructAdvice();

	}

}
