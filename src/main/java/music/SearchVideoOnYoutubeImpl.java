package music;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import java.io.IOException;
import java.util.Random;

public class SearchVideoOnYoutubeImpl implements Searchable {

    private static YouTube youtube;
    private String[] wordsForRandomVideo = new String[]{"mistmorn", "lofi",
            "depressed songs", "depressed lofi", "rap", "phonk", "zxc ghoul phonk", "convolk", "lil peep",
            "slowed song", "nightcore", "xxxtentencion", "instrumental", "slowed instrumental", ""};

    public void initialize() {
        youtube  = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer()  {
            @Override
            public void initialize(HttpRequest httpRequest) throws IOException {}
        }).setApplicationName("youtube-cmdline-search-sample").build();
    }

    /**
     * @param keyword - word for search video on youtube
     * @return - string of video url
     */

    @Override
    public String searchVideoByKeyword(String keyword) throws IOException {
        return "https://www.youtube.com/watch?v=" +
                getUrlFromResponse(getResult(keyword, 0));
    }

    @Override
    public String searchRandomVideo() throws IOException {
        Random r = new Random();
        int random = r.nextInt(5);
        String randomWordFromArray = getRandomWordFromArray();
        return "https://www.youtube.com/watch?v=" +
                getUrlFromResponse(getResult(randomWordFromArray, random));
    }

    public SearchResult getResult(String keyword, int random) throws IOException {
        YouTube.Search.List search = youtube.search().list("snippet");
        search.setKey(YoutubeApiData.YOUTUBE_API_KEY);
        search.setQ(keyword);
        search.setType("video");
        SearchListResponse response = search.execute();
        return response.getItems().get(random);
    }


    public String getUrlFromResponse(SearchResult result) {
        return result.getId().getVideoId();
    }

    private String getRandomWordFromArray() {
        Random r = new Random();
        int random = r.nextInt(wordsForRandomVideo.length - 1);
        return wordsForRandomVideo[random];
    }
}
