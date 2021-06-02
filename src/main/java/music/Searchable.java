package music;

import java.io.IOException;

public interface Searchable {
    /**
     *
     * @param keyword - word for search video on youtube
     * @return - string of video url
     *
     */
    String searchVideoByKeyword(String keyword) throws IOException;

    String searchRandomVideo() throws IOException;
}
