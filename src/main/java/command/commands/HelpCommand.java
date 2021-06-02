package command.commands;

import command.Command;
import exceptions.SoundNotFoundException;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import sendmessages.SendMessageInterface;

import java.awt.*;

public class HelpCommand implements Command {
    private final SendMessageInterface sender;
    private static final String HELP_URL = "https://mikunakano.bot/help";
    public HelpCommand(SendMessageInterface sender) {
        this.sender = sender;
    }

    @Override
    public void execute(GuildMessageReceivedEvent event) throws SoundNotFoundException {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Help on my website");
        builder.setDescription("[Miku Nakano website <3]" + "(" + HELP_URL +")");
        builder.setColor(Color.PINK);
        event.getChannel().sendMessage(sender.sendEmbedMessage(builder.build()).build()).queue();

    }
}
