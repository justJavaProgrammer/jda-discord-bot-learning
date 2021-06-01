package connnect;

import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import sendmessages.SendMessageImpl;

public class ConnectToTheVoiceChannelService implements Connectable {
    private GuildMessageReceivedEvent event;
    private VoiceChannel voiceChannel;

    public ConnectToTheVoiceChannelService() {
    }

    public ConnectToTheVoiceChannelService(GuildMessageReceivedEvent event) {
        this.event = event;
    }

    @Override
    public void connectToTheVoiceChannel(GuildMessageReceivedEvent event) {
        SendMessageImpl sender = new SendMessageImpl();
        this.voiceChannel = event.getMember().getVoiceState().getChannel();
        if (this.voiceChannel != null) {
            AudioManager audioManager = event.getGuild().getAudioManager();
            audioManager.openAudioConnection(voiceChannel);
        } else sender.sendMessage(event, "You are not in voice channel");

    }
}
