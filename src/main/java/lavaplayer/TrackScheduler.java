package lavaplayer;


import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import sendmessages.SendMessageImpl;

import java.awt.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class TrackScheduler extends AudioEventAdapter {

    private AudioPlayer player;
    private BlockingQueue<AudioTrack> queue;
    private AudioTrack track;
    private GuildMessageReceivedEvent event;
    private SendMessageImpl sender;
    private AudioTrack head;
    private boolean stopLoop = false;

    public TrackScheduler(AudioPlayer player) {
        this.player = player;
        this.queue = new LinkedBlockingDeque<>();
        this.sender = new SendMessageImpl();
    }

    public void queue(AudioTrack track) {
        if (!this.player.startTrack(track, true)) {
            this.queue.offer(track);
        }
        if(this.queue.size() == 0) {
            event.getChannel().sendMessage(sender.sendEmbedMessage(track).build()).queue();
            this.head = track;
        } else {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle("Queued");
            builder.setColor(Color.PINK);
            builder.setDescription("[" + track.getInfo().title + "]" + "(" + track.getInfo().uri + ")");
            event.getChannel().sendMessage(sender.sendEmbedMessage(track, builder.build()).build()).queue();
        }
    }
    public void playlistQueue(AudioTrack track) {
        if (!this.player.startTrack(track, true)) {
            this.queue.offer(track);
        }
        if(this.queue.size() == 0) {
            event.getChannel().sendMessage(sender.sendEmbedMessage(track).build()).queue();
        }
    }
    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        this.track = track;
        if (endReason.mayStartNext) {
            nextTrack();
        }
    }

    public void nextTrack() {
        this.track = this.queue.poll();
        this.head = this.track;
        this.player.startTrack(this.track, false);
        PlayerManager.getInstance().setSize(PlayerManager.getInstance().getSize()-1);
        sendEmbedMessage(this.event);
    }

    public void skip() {
        player.stopTrack();
        if (queue.size() >= 1) {
            nextTrack();
        }
    }

    public void clear() {
        queue.clear();
    }

    public void pause() {
        this.player.setPaused(true);
    }

    public void unpause() {
        this.player.setPaused(false);
    }
    public void setEvent(GuildMessageReceivedEvent event) {
        this.event = event;
    }

    public void sendEmbedMessage(GuildMessageReceivedEvent event) {
        event.getChannel().sendMessage(sender.sendEmbedMessage(this.track).build()).queue();
    }

    public void loop() {
        AudioTrack track = head;
        while(stopLoop) {
            player.startTrack(track.makeClone(), true);
        }
    }
    public void setStopLoop(boolean stopLoop) {
        System.out.println("set stop loop");
        this.stopLoop = stopLoop;
    }
    public AudioTrack getTrack() {
        return this.head;
    }
}
