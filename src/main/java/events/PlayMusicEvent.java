package events;

import sx.blah.discord.handle.obj.IMessage;

/**
 * Event for when user asks to play music
 * @author King
 *
 */
public class PlayMusicEvent extends CustomEvent {

	public PlayMusicEvent(IMessage message) {
		super(message);
	}

}
