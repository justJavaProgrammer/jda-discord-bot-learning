package music.spotify;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import com.wrapper.spotify.requests.data.tracks.GetTrackRequest;
import exceptions.SoundNotFoundException;
import music.Playable;
import org.apache.commons.collections4.Get;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.net.URI;

public class PlayMusicFromSpotify  {
    private static SpotifyApi spotifyApi;
    private GetTrackRequest trackRequest;
    private static AuthorizationCodeUriRequest request;
    private static URI redirect = SpotifyHttpManager.makeUri("https://example.com/spotify-redirect");
    public static void initialize() {
         spotifyApi =  new SpotifyApi.Builder().
                setClientId("2dc23db61bf344139bf140cff6fb8474")
                .setClientSecret("198c38516b5441ddaf47593770f4fcd0")
                 .setRedirectUri(redirect)
                .build();
         request = spotifyApi.authorizationCodeUri().build();
    }

    public String getTitleAndAuthorFromSpotifyApi(String id) throws ParseException, SpotifyWebApiException, IOException {
        GetTrackRequest track = spotifyApi.getTrack(id).build();
        return track.execute().getName();
    }
}
