package lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

public class GuildMusicManager   {

    private AudioPlayer audioPlayer;
    private TrackScheduler scheduler;
    private AudioPlayerSendHandler audioPlayerSendHandler;

    public GuildMusicManager(AudioPlayerManager manager) {
        this.audioPlayer = manager.createPlayer();
        this.scheduler = new TrackScheduler(audioPlayer);
        this.audioPlayer.addListener(scheduler);
        this.audioPlayerSendHandler = new AudioPlayerSendHandler(this.audioPlayer);
    }

    public AudioPlayerSendHandler getAudioPlayerSendHandler() {
        return this.audioPlayerSendHandler;
    }
    public AudioPlayer getAudioPlayer() {
        return this.audioPlayer;
    }

    public TrackScheduler getScheduler() {
        return this.scheduler;
    }
}
