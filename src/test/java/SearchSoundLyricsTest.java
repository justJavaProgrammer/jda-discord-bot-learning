import core.GLA;
import genius.Lyrics;

import java.util.List;

public class SearchSoundLyricsTest {
    public static void main(String[] args) {
        GLA gla = new GLA("ClhCKH_3GZDsOmxH3lB2oqbD9NVVNLEzSKrhxBdqJ3hL0IpRS7--d468KMXHGlbW",
                "LQOFjpxlXKpwkTviEIWR_-SijlDjBkk3pQOXXdwsRcdfrMPYTGY5OoWnPM8K0UAt");
        List<Lyrics> lil_peep_alone = gla.search("lil peep alone");
        System.out.println(lil_peep_alone.get(0).getText());
    }

}
