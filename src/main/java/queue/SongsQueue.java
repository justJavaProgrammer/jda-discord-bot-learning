package queue;

import java.util.Queue;

public class SongsQueue {
    private static Queue<String> songs;

    public SongsQueue(Queue<String> songs) {
        this.songs = songs;
    }

    public void addSongToQueue(String song) {
        songs.add(song);
    }
    public String getSong() {
        return songs.peek();
    }

    public void clear() {
        songs.clear();
    }
    public static  Queue<String> getSongs() {
        return songs;
    }
}
