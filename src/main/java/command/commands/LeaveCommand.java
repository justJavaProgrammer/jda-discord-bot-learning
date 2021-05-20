package command.commands;

import command.Command;
import disconnect.Disconnectable;
import exceptions.SoundNotFoundException;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class LeaveCommand implements Command, Disconnectable {
    @Override
    public void execute(GuildMessageReceivedEvent event) throws SoundNotFoundException {
        disconnect(event);
    }

    @Override
    public void disconnect(GuildMessageReceivedEvent event) {
        VoiceChannel voiceChannel = event.getMember().getVoiceState().getChannel();
        AudioManager audioManager = event.getGuild().getAudioManager();
        audioManager.closeAudioConnection();
    }
}
