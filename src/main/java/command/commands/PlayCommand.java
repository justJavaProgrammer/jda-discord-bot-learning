package command.commands;

import command.Command;
import connnect.ConnectToTheVoiceChannelService;
import exceptions.SoundNotFoundException;
import lavaplayer.PlayerManager;
import music.SearchVideoOnYoutubeImpl;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import sendmessages.SendMessageInterface;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class PlayCommand implements Command {

    private SendMessageInterface sendMessageInterface;
    private GuildMessageReceivedEvent event;
    private ConnectToTheVoiceChannelService connect;
    private PlayerManager playerManager;
    private SearchVideoOnYoutubeImpl youtubeMusicSearcher;

    public PlayCommand(SendMessageInterface sendMessageInterface) {
        this.sendMessageInterface = sendMessageInterface;
        this.playerManager = new PlayerManager();
        this.youtubeMusicSearcher = new SearchVideoOnYoutubeImpl();
    }

    @Override
    public void execute(GuildMessageReceivedEvent event) throws SoundNotFoundException {
        this.event = event;
        this.connect = new ConnectToTheVoiceChannelService();
        String url = getSongUrl(event.getMessage().getContentRaw());
        connect.connectToTheVoiceChannel(event);
        PlayerManager.getInstance().setEvent(event);
        try {
            distribution(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PlayerManager.getInstance()
                .getMusicManager(event.getGuild())
                .getScheduler().setEvent(event);
    }

    private String getSongUrl(String s) throws SoundNotFoundException {
        if(s.length() >= 5)
            return s.substring(5).trim();
        sendMessageInterface.sendMessage(event, "enter sound name");
        throw new SoundNotFoundException();
    }
    public void play(String trackUrl)  {
        PlayerManager.getInstance().loadAndPlay(event.getChannel(), trackUrl);
    }

    private void distribution(String text) throws IOException {
        try {
            new URL(text);
            play(text);
        } catch (MalformedURLException e) {
            play(youtubeMusicSearcher.searchVideoByKeyword(text));
        }
    }
}