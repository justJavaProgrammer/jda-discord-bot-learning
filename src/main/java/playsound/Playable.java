package playsound;

import exceptions.SoundNotFoundException;

public interface Playable {
    /**
     * @param url - url of song that must to play
     *            if song can't be found -
     * @throws SoundNotFoundException
     */
    void play(String url) throws SoundNotFoundException;
}
