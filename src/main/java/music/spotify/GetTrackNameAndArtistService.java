package music.spotify;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.*;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistRequest;
import com.wrapper.spotify.requests.data.tracks.GetTrackRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public class GetTrackNameAndArtistService {


    public String getTrackName(GetTrackRequest spotifyTrack) throws ParseException, SpotifyWebApiException, IOException {
        return spotifyTrack.execute().getName();
    }

    public String[] getArtistsName(GetTrackRequest spotifyTrack) throws ParseException, SpotifyWebApiException, IOException {
        final ArtistSimplified[] artists = spotifyTrack.execute().getArtists();
        String[] artistNames = new String[artists.length];
        for (int i = 0; i < artists.length; i++) {
            artistNames[i] = artists[i].getName();
        }
        return artistNames;
    }
}
