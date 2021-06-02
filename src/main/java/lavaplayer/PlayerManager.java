package lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import sendmessages.SendMessageImpl;
import sendmessages.SendMessageInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerManager {

    private static PlayerManager INSTANCE;
    private Map<Long, GuildMusicManager> managers;
    private AudioPlayerManager audioPlayerManager;
    private GuildMessageReceivedEvent event;
    private int size = 0;
    private SendMessageInterface sender;

    public PlayerManager() {
        this.audioPlayerManager = new DefaultAudioPlayerManager();
        this.managers = new HashMap<>();
        this.sender = new SendMessageImpl();
        AudioSourceManagers.registerRemoteSources(this.audioPlayerManager);
        AudioSourceManagers.registerLocalSource(this.audioPlayerManager);
    }

    public GuildMusicManager getMusicManager(Guild guild) {
        return this.managers.computeIfAbsent(guild.getIdLong(), (guildId) -> {
            GuildMusicManager guildMusicManager = new GuildMusicManager(this.audioPlayerManager);
            guild.getAudioManager().setSendingHandler(guildMusicManager.getAudioPlayerSendHandler());
            return guildMusicManager;
        });
    }

    public void loadAndPlay(TextChannel channel, String trackUrl) {
        final GuildMusicManager musicManager = this.getMusicManager(channel.getGuild());
        audioPlayerManager.loadItemOrdered(channel, trackUrl, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack audioTrack) {
                musicManager.getScheduler().queue(audioTrack);
            }

            @Override
            public void playlistLoaded(AudioPlaylist audioPlaylist) {
                size += audioPlaylist.getTracks().size();
                List<AudioTrack> tracks = audioPlaylist.getTracks();
                EmbedBuilder builder = new EmbedBuilder();
                builder.setTitle("Queued " + size + " tracks");
                event.getChannel().sendMessage(sender.sendEmbedMessage(builder.build()).build()).queue();
                for (AudioTrack track : tracks)
                    musicManager.getScheduler().playlistQueue(track);
            }

            @Override
            public void noMatches() {

            }

            @Override
            public void loadFailed(FriendlyException e) {

            }
        });

    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }

    public GuildMessageReceivedEvent getEvent() {
        return this.event;
    }
    public void setEvent(GuildMessageReceivedEvent event) {
        this.event = event;
    }
    public static PlayerManager getInstance() {
        if (INSTANCE == null)
            INSTANCE = new PlayerManager();
        return INSTANCE;
    }
}
