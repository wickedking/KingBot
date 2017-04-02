package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;

import constants.BotConstants;
import events.CreatePollEvent;
import login.Authorization;
import sx.blah.discord.handle.impl.obj.PrivateChannel;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;
import sx.blah.discord.util.RequestBuffer;



/**
 * Utility class to provide common methods for posting messages and other methods
 * @author King
 *
 */
public class Utils {

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
	private static List<String> jokeList = constructJokeList();
	
	/**
	 * Reference to the list of 'insults'
	 */
	private static List<String> insultList = constructInsultList();
	
	/**
	 * Reference to the list of 'advice'
	 */
	private static List<String> adviceList = constructAdvice();

	/**
	 * Used to save user created Keywords
	 */
	public void saveKeywords() {
		//TODO implement
	}

	/**
	 * Logs message with the keywords for the server
	 * @return
	 */
	public Map<String, String> getKeywords(){

		return null;
	}

	/**
	 * Writes specified message to the specified channel
	 * @param message
	 * @param channel
	 */
	public static void writeMessageToChannel(String message, IChannel channel){
		//edit message to remove the actual mentions so logging doesnt mention people
		RequestBuffer.request(() -> {
			MessageBuilder builder = new MessageBuilder(Authorization.client).withContent(message);
			builder.withChannel(channel);
			try {
				builder.build();
			} catch (DiscordException | MissingPermissionsException e) {
				logger.error(e);
			}

		});

	}

	/**
	 * Will private message the user with the message
	 * @param user
	 * @param message
	 */
	public static void privateMessageUser(IUser user, String message){
		PrivateChannel dm = new PrivateChannel(Authorization.client, user, message);
		try {
			dm.sendMessage("blargh");
		} catch (RateLimitException | MissingPermissionsException | DiscordException e) {
			logger.error(e);
		}
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
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream("8ball.txt")));
		List<String> list =  br.lines().collect(Collectors.toList());
		try {
			br.close();
		} catch (IOException e) {
			logger.warn(e.getStackTrace());
		}
		return list;
		
	}


	/**
	 * Returns a random joke from the list
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
	private static List<String> constructJokeList(){
		List<String> jokeList = new ArrayList<String>();

		BufferedReader br = new BufferedReader(new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream("jokes.txt")));
		jokeList = br.lines().collect(Collectors.toList());
		try {
			br.close();
		} catch (IOException e) {
			logger.warn(e.getStackTrace());
		}
		
		return jokeList;
	}
	
	/**
	 * Constructs the list of insults
	 * @return the list of insults
	 */
	private static List<String> constructInsultList(){
		List<String> insultList = new ArrayList<String>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream("insults.txt")));
		insultList = br.lines().collect(Collectors.toList());
		try {
			br.close();
		} catch (IOException e) {
			logger.warn(e.getStackTrace());
		}
		
		return insultList;

	}
	
	/**
	 * Constructs the list of advice
	 * @return the list of advice
	 */
	private static List<String> constructAdvice() {
		List<String> adviceList = new ArrayList<String>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream("advice.txt")));
		adviceList = br.lines().collect(Collectors.toList());
		try {
			br.close();
		} catch (IOException e) {
			logger.warn(e.getStackTrace());
		}
		
		return adviceList;
	}

	/**
	 * Used to get a poll from strawpoll
	 * @param message the id of the poll to retrieve
	 * 
	 * TODO finish
	 */
	public static void getPoll(String message){
		String id = message.replace("`getpoll ", "");
		try {
			GetRequest request = Unirest.get("https://www.strawpoll.me/api/v2/polls/{id}");
			request.routeParam("id", id);
			HttpResponse<JsonNode> jsonResponse = request.asJson();
			System.out.println(jsonResponse.getBody().toString());
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			logger.warn(e.getStackTrace());
		}
	}

	/**
	 * Used to create a poll. Parses input string to create message params
	 * @param event
	 */
	public static void createPoll(CreatePollEvent event) {
		String title;
		String[] options;
		boolean multi;
		String message = event.getMessage().getContent();
		message = message.substring(12);
		int index = message.indexOf("|");
		title = message.substring(0, index);
		message = message.substring(index + 1).trim();
		System.out.println(message);
		options = message.split("\\|");
		//title = options[0];
		multi = Boolean.getBoolean(options[options.length -1]);
		System.out.println("_____________________");
		System.out.println(title);
		for(String out : options){
			System.out.println(out);
		}
		System.out.println(multi);
		HttpResponse<JsonNode> jsonResponse = null;
		try {
			jsonResponse = Unirest.post("https://www.strawpoll.me/api/v2/polls").field("title", title).field("options", options).field("multi", multi).asJson();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(jsonResponse.getStatus());
		System.out.println(jsonResponse.getStatusText());
		System.out.println(jsonResponse.getBody());
		System.out.println(jsonResponse.getHeaders());
	}

}
