package command.commands;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistsItemsRequest;
import command.Command;
import connnect.ConnectToTheVoiceChannelService;
import exceptions.SoundNotFoundException;
import lavaplayer.PlayerManager;
import music.SearchVideoOnYoutubeImpl;
import music.spotify.PlayMusicFromSpotify;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.apache.hc.core5.http.ParseException;
import org.springframework.cglib.transform.AbstractProcessTask;
import sendmessages.SendMessageInterface;
import services.PlayCommandService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class PlayCommand implements Command {

    private SendMessageInterface sendMessageInterface;
    private GuildMessageReceivedEvent event;
    private ConnectToTheVoiceChannelService connect;
    private PlayCommandService playCommandService;

    public PlayCommand(SendMessageInterface sendMessageInterface) {
        this.sendMessageInterface = sendMessageInterface;
        playCommandService = new PlayCommandService();
    }

    @Override
    public void execute(GuildMessageReceivedEvent event) throws SoundNotFoundException {
        this.event = event;
        this.connect = new ConnectToTheVoiceChannelService();
        String url = getSongUrl(event.getMessage().getContentRaw());
        connect.connectToTheVoiceChannel(event);
        PlayerManager.getInstance().setEvent(event);
        try {
            playCommandService.distribution(url, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PlayerManager.getInstance()
                .getMusicManager(event.getGuild())
                .getScheduler().setEvent(event);
    }

    private String getSongUrl(String s) throws SoundNotFoundException {
        if (s.trim().length() > 5)
            return s.substring(5).trim();
        sendMessageInterface.sendMessage(event, "enter sound name");
        throw new SoundNotFoundException();
    }
}