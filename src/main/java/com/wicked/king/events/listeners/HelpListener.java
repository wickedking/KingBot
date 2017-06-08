package com.wicked.king.events.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wicked.king.constants.BotConstants;
import com.wicked.king.events.HelpEvent;
import com.wicked.king.utils.Utils;

import sx.blah.discord.api.events.EventSubscriber;

/**
 * Listener for when a user asks for help with a command
 *
 * @author King
 *
 */
public class HelpListener {

    private static final Logger logger = LogManager.getLogger(HelpListener.class);

    /**
     * When a user asks for help with a command, will display a helpful message.
     *
     * @param event
     */
    @EventSubscriber
    public void onHelp(final HelpEvent event) {

        String message = event.getMessage().getContent();
        logger.warn("Help event: " + message);
        message = message.substring("`help ".length());
        logger.warn("Help event2: " + message);
        String output = "";
        if (message.startsWith("prune")) {
            output = BotConstants.HELP_PRUNE;

        } else if (message.startsWith("rank")) {
            output = BotConstants.HELP_RANK;

        } else if (message.startsWith("commands")) {
            output = BotConstants.HELP_COMMANDS;

        } else if (message.startsWith("kick ")) {
            output = BotConstants.HELP_KICK;

        } else if (message.startsWith("ban ")) {
            output = BotConstants.HELP_BAN;

        } else if (message.startsWith("serverinfo")) {
            output = BotConstants.HELP_SERVERINFO;

        } else if (message.startsWith("playmusic")) {
            output = BotConstants.HELP_PLAYMUSIC;

        } else if (message.startsWith("`about")) {
            output = BotConstants.HELP_ABOUT;

        }

        Utils.writeMessageToChannel(output, event.getMessage().getChannel());

    }
}
