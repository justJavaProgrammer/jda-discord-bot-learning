package services;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import lavaplayer.PlayerManager;
import music.SearchVideoOnYoutubeImpl;
import music.spotify.PlayMusicFromSpotify;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class PlayCommandService {
    private PlayerManager playerManager;
    private SearchVideoOnYoutubeImpl youtubeMusicSearcher;
    private PlayMusicFromSpotify spotify;

    public PlayCommandService() {
        this.playerManager = new PlayerManager();
        this.youtubeMusicSearcher = new SearchVideoOnYoutubeImpl();
        this.spotify = new PlayMusicFromSpotify();
    }

    public void play(String trackUrl, GuildMessageReceivedEvent event) {
        System.out.println("play:");
        PlayerManager.getInstance().loadAndPlay(event.getChannel(), trackUrl);
    }

    public void distribution(String text, GuildMessageReceivedEvent event) throws IOException {
        try {
            new URL(text);
            if (isSpotifyURL(text)) {
                if (isSpotifyPlaylist(text)) {
                    String playlistId = text.substring(34, text.lastIndexOf('?'));
                    System.out.println(playlistId);
                    final String[] playlistItems = spotify.getPlaylistItems(playlistId);
                    for (final String item : playlistItems) {
                        final String video = youtubeMusicSearcher.searchVideoByKeyword(item);
                        play(video, event);
                    }
                } else {
                    String songId = text.substring(31, text.lastIndexOf('?'));
                    System.out.println("song id: " + songId);
                    final String titleAndAuthorFromSpotifyApi = spotify.getTitleAndAuthorFromSpotifyApi(songId);
                    final String songURL = youtubeMusicSearcher.searchVideoByKeyword(titleAndAuthorFromSpotifyApi);
                    play(songURL, event);
                }
            } else {
                play(text, event);
            }
        } catch (MalformedURLException e) {
            play(youtubeMusicSearcher.searchVideoByKeyword(text), event);
        } catch (ParseException e) {
            System.out.println("ParseException ");
            e.printStackTrace();
        } catch (SpotifyWebApiException e) {
            System.out.println("SpotifyWebApiException ");
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
