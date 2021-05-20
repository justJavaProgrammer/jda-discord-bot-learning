package disconnect;

import listeners.MessageListener;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import sendmessages.SendMessageImpl;

public class DisconnectFromTheVoiceChannel implements Disconnectable {
    private GuildMessageReceivedEvent event;
    private MessageListener listener;

    public DisconnectFromTheVoiceChannel(MessageListener listener) {
        this.listener = listener;
    }

    @Override
    public void disconnect(GuildMessageReceivedEvent event) {
        SendMessageImpl sender = new SendMessageImpl();
        AudioManager audioManager = event.getGuild().getAudioManager();
        if(listener.getMessage().equalsIgnoreCase("/leave"))
        audioManager.closeAudioConnection();
    }
}
