package sendmessages;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public interface SendMessageInterface {

    void sendMessage(GuildMessageReceivedEvent event, String answer);
    EmbedBuilder sendEmbedMessage(AudioTrack track);
    EmbedBuilder sendEmbedMessage(AudioTrack track, MessageEmbed embed);
    EmbedBuilder sendEmbedMessage(MessageEmbed embed);
}
