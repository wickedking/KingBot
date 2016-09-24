package eventListeners;

import events.HelpEvent;
import sx.blah.discord.api.events.EventSubscriber;

/**
 * Listener for when a user asks for help with a command
 * @author King
 *
 */
public class HelpListener {
	
	/**
	 * When a user asks for help with a command
	 * @param event
	 */
	@EventSubscriber
	public void onHelp(HelpEvent event){
		//TODO implement
	}

}
