package command.commands;

import command.Command;
import disconnect.Disconnectable;
import exceptions.SoundNotFoundException;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import sendmessages.SendMessageInterface;

import java.awt.*;

public class LeaveCommand implements Command, Disconnectable {
    private SendMessageInterface sender;

    public LeaveCommand(SendMessageInterface sender) {
        this.sender = sender;
    }
    @Override
    public void execute(GuildMessageReceivedEvent event) throws SoundNotFoundException {
        disconnect(event);
    }

    @Override
    public void disconnect(GuildMessageReceivedEvent event) {
        AudioManager audioManager = event.getGuild().getAudioManager();
        audioManager.closeAudioConnection();
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("successfully logged out of the voice chat").setColor(Color.PINK);
        event.getChannel().sendMessage(sender.sendEmbedMessage(builder.build()).build()).queue();;
    }
}
