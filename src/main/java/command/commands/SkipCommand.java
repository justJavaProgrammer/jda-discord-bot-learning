package command.commands;

import command.Command;
import exceptions.SoundNotFoundException;
import lavaplayer.PlayerManager;
import lavaplayer.TrackScheduler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import sendmessages.SendMessageInterface;

import javax.sound.midi.Track;
import java.awt.*;

public class SkipCommand implements Command {

    private SendMessageInterface sender;

    public SkipCommand(SendMessageInterface sender) {
        this.sender = sender;
    }

    @Override
    public void execute(GuildMessageReceivedEvent event) throws SoundNotFoundException {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Skip song").setColor(Color.BLUE);
        event.getChannel().sendMessage(sender.sendEmbedMessage(builder.build()).build()).queue();
        PlayerManager.getInstance().getMusicManager(event.getGuild()).getScheduler().skip();
    }
}
