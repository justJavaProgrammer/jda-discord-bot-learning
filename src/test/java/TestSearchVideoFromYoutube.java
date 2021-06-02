import music.SearchVideoOnYoutubeImpl;

import java.io.IOException;

public class TestSearchVideoFromYoutube {
    public static void main(String[] args) throws IOException {
        SearchVideoOnYoutubeImpl search = new SearchVideoOnYoutubeImpl();
        search.initialize();
        System.out.println(search.searchVideoByKeyword("convolk dead in december"));

    }
}
