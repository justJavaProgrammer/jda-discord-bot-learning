package music.spotify;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistRequest;
import com.wrapper.spotify.requests.data.tracks.GetTrackRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public class PlayMusicFromSpotify {
    private static final String clientId = "2dc23db61bf344139bf140cff6fb8474";
    private static final String clientSecret = "198c38516b5441ddaf47593770f4fcd0";
    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .build();
    private GetTrackNameAndArtistService service;

    private static final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
            .build();

    public PlayMusicFromSpotify() {
        try {
            final String accessToken = clientCredentialsRequest.execute().getAccessToken();
            spotifyApi.setAccessToken(accessToken);
        } catch (IOException e) {

        } catch (SpotifyWebApiException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        service = new GetTrackNameAndArtistService();

    }
    public String getTitleAndAuthorFromSpotifyApi(String id) throws ParseException, SpotifyWebApiException, IOException {
        GetTrackRequest track = spotifyApi.getTrack(id).build();
        final String[] artists = service.getArtistsName(track);
        final String trackName = service.getTrackName(track);
        String songFullName = "";
        for (int i = 0; i < artists.length; i++) {
            songFullName += artists[i] + " ";
        }
        return songFullName + trackName;
    }

    public String[] getPlaylistItems(String playlistId) throws ParseException, SpotifyWebApiException, IOException {
        GetPlaylistRequest request = spotifyApi.getPlaylist(playlistId).build();
        final Paging<PlaylistTrack> tracks = request.execute().getTracks();
        final PlaylistTrack[] items = tracks.getItems();
        String[] result = new String[tracks.getItems().length];
        for (int i = 0; i < items.length; i++) {
            result[i] = getTitleAndAuthorFromSpotifyApi(items[i].getTrack().getId());
        }

        return result;
    }
}
