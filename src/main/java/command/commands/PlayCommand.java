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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class PlayCommand implements Command {

    private SendMessageInterface sendMessageInterface;
    private GuildMessageReceivedEvent event;
    private ConnectToTheVoiceChannelService connect;
    private PlayerManager playerManager;
    private SearchVideoOnYoutubeImpl youtubeMusicSearcher;
    private PlayMusicFromSpotify spotify;

    public PlayCommand(SendMessageInterface sendMessageInterface) {
        this.sendMessageInterface = sendMessageInterface;
        this.playerManager = new PlayerManager();
        this.youtubeMusicSearcher = new SearchVideoOnYoutubeImpl();
        this.spotify = new PlayMusicFromSpotify();
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
        if (s.trim().length() > 5)
            return s.substring(5).trim();
        sendMessageInterface.sendMessage(event, "enter sound name");
        throw new SoundNotFoundException();
    }

    public void play(String trackUrl) {
        System.out.println("play:");
        PlayerManager.getInstance().loadAndPlay(event.getChannel(), trackUrl);
    }

    private void distribution(String text) throws IOException {
        try {
            new URL(text);
            if (isSpotifyURL(text)) {
                if (isSpotifyPlaylist(text)) {
                    String playlistId = text.substring(34, text.lastIndexOf('?'));
                    System.out.println(playlistId);
                    final String[] playlistItems = spotify.getPlaylistItems(playlistId);
                    for (int i = 0; i < playlistItems.length; i++) {
                        final String item = playlistItems[i];
                        System.out.println(item);
                        final String video = youtubeMusicSearcher.searchVideoByKeyword(item);
                        play(video);
                    }
                } else {
                    String songId = text.substring(25, text.lastIndexOf('?'));
                    final String titleAndAuthorFromSpotifyApi = spotify.getTitleAndAuthorFromSpotifyApi(songId);
                    final String songURL = youtubeMusicSearcher.searchVideoByKeyword(titleAndAuthorFromSpotifyApi);
                    play(songURL);
                }
            } else {
                play(text);
            }
        } catch (MalformedURLException e) {
            play(youtubeMusicSearcher.searchVideoByKeyword(text));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (SpotifyWebApiException e) {
            e.printStackTrace();
        }
    }

    public boolean isSpotifyURL(String url) {
        return url.startsWith("https://open.spotify.com/");
    }

    public boolean isSpotifyPlaylist(String url) {
        return url.startsWith("https://open.spotify.com/playlist/");
    }
}