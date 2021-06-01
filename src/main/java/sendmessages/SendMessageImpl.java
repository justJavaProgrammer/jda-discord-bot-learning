package sendmessages;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;

public class SendMessageImpl implements SendMessageInterface {

    @Override
    public void sendMessage(GuildMessageReceivedEvent event, String answer) {
        event.getChannel().sendMessage(answer).queue();
    }

    @Override
    public EmbedBuilder sendEmbedMessage(AudioTrack track) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Now playing");
        builder.setDescription("[" + track.getInfo().title + "]" + "(" + track.getInfo().uri + ")");
        builder.setColor(Color.CYAN);
        return builder;
    }

    @Override
    public EmbedBuilder sendEmbedMessage(AudioTrack track, MessageEmbed embed) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(embed.getTitle());
        builder.setDescription(embed.getDescription());
        builder.setColor(embed.getColor());
        return builder;
    }

    @Override
    public EmbedBuilder sendEmbedMessage(MessageEmbed embed) {
        EmbedBuilder builder = new EmbedBuilder();
        return builder.setTitle(embed.getTitle()).setColor(embed.getColor())
                .setDescription(embed.getDescription());
    }
}
