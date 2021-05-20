package command.commands;

import command.Command;
import connnect.ConnectToTheVoiceChannelService;
import exceptions.SoundNotFoundException;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import playsound.Playable;
import queue.SongsQueue;
import sendmessages.SendMessageInterface;

import java.util.LinkedList;

public class PlayCommand implements Command, Playable {

    private SendMessageInterface sendMessageInterface;
    private SongsQueue queue;
    private VoiceChannel channel;
    private AudioManager audioManager;
    private GuildMessageReceivedEvent event;
    private ConnectToTheVoiceChannelService connect;

    public PlayCommand(SendMessageInterface sendMessageInterface) {
        this.sendMessageInterface = sendMessageInterface;
        queue = new SongsQueue(new LinkedList<>());
    }

    @Override
    public void execute(GuildMessageReceivedEvent event) {
        this.event = event;
        this.connect = new ConnectToTheVoiceChannelService();
        String url = "";
        try {
           url = getSongUrl(event.getMessage().getContentRaw());
            queue.addSongToQueue(url);
        } catch (SoundNotFoundException e) {
            System.out.println("sound message error");
            return;
        }
        sendMessageInterface.sendMessage(event, "Now playing: " + url);

        connect.connectToTheVoiceChannel(event);
        play(url);
    }

    @Override
    public void play(String str)  {

    }

    private String getSongUrl(String s) throws SoundNotFoundException {
        String[] song = s.split(" ");
        if (song.length > 1)
            return song[1].trim();
        sendMessageInterface.sendMessage(event, "enter sound name");
        throw new SoundNotFoundException();
    }
}
