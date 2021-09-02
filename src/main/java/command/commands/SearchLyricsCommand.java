package command.commands;

import command.Command;
import core.GLA;
import exceptions.SoundNotFoundException;
import genius.Lyrics;
import lavaplayer.PlayerManager;
import listeners.MessageListener;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import sendmessages.SendMessageInterface;

import java.awt.*;
import java.util.List;

public class SearchLyricsCommand implements Command {
    private SendMessageInterface sender;
    private static GLA genius;

    public SearchLyricsCommand(SendMessageInterface sender) {
        this.sender = sender;
    }

    @Override
    public void execute(GuildMessageReceivedEvent event) throws SoundNotFoundException {
        String[] split = MessageListener.getMessage().split(" ");
        if (split.length == 1) {
            String songInfo = PlayerManager.getInstance().getMusicManager(event.getGuild())
                    .getScheduler().getTrack().getInfo().title.trim();
            searchLyrics(event, songInfo);
        } else {
            String songName = MessageListener.getMessage().substring(7);
            System.out.println(songName);
            searchLyrics(event, songName);
        }
    }

    private String parseString(String text) {
        int lastIndex = 0;
        String parsed = text;
        if (text.contains("[")) {
            lastIndex = text.indexOf("[");
            parsed = text.substring(0, lastIndex);
        } else if (text.contains("(")) {
            lastIndex = text.indexOf("(");
            parsed = text.substring(0, lastIndex);
        } else if (text.contains("slowed")) {
            lastIndex = text.indexOf("slowed");
            parsed = text.substring(0, lastIndex);
        }
        return parsed.trim();
    }

    public static void initialize() {
        genius = new GLA("ClhCKH_3GZDsOmxH3lB2oqbD9NVVNLEzSKrhxBdqJ3hL0IpRS7--d468KMXHGlbW",
                "LQOFjpxlXKpwkTviEIWR_-SijlDjBkk3pQOXXdwsRcdfrMPYTGY5OoWnPM8K0UAt");
    }

    public void searchLyrics(GuildMessageReceivedEvent event, String songInfo) {
        try {
            List<Lyrics> search = genius.search(parseString(songInfo));
            System.out.println(parseString(songInfo));
            String parsedString = (search.get(0).getText());
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle("Lyrics of " + songInfo);
            builder.setDescription(parsedString);
            builder.setColor(Color.PINK);
            event.getChannel().sendMessage(sender.sendEmbedMessage(builder.build()).build()).queue();
        } catch (IndexOutOfBoundsException e) {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle("Sorry, but i can't find lyrics:(");
            event.getChannel().sendMessage(sender.sendEmbedMessage(builder.build()).build()).queue();
        }
    }

}
