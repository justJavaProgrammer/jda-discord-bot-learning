package music;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import exceptions.SoundNotFoundException;


public interface Playable {
    /**
     * @param track - track  that must to play
     *            if song can't be found -
     * @throws SoundNotFoundException
     */
    void play(AudioTrack track) throws SoundNotFoundException;
}
