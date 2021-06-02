import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import music.spotify.PlayMusicFromSpotify;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public class SpotifyApiTest {
    public static void main(String[] args) throws ParseException, SpotifyWebApiException, IOException {
        PlayMusicFromSpotify playMusicFromSpotify = new PlayMusicFromSpotify();
        PlayMusicFromSpotify.initialize();
        String s = playMusicFromSpotify.getTitleAndAuthorFromSpotifyApi("01iyCAUm8EvOFqVWYJ3dVX");
        System.out.println(s);
    }
}
