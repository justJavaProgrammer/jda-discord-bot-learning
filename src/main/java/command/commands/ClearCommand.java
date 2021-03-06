package command.commands;

import command.Command;
import lavaplayer.PlayerManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import sendmessages.SendMessageInterface;

import java.awt.*;

public class ClearCommand implements Command {
    private SendMessageInterface sender;

    public ClearCommand(SendMessageInterface sender) {
        this.sender = sender;
    }

    @Override
    public void execute(GuildMessageReceivedEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Successful cleared queue").setColor(Color.PINK);
        event.getChannel().sendMessage(sender.sendEmbedMessage(builder.build()).build()).queue();
        PlayerManager.getInstance().getMusicManager(event.getGuild()).getScheduler().clear();
    }
}
