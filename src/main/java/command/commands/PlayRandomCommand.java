package command.commands;

import command.Command;
import connnect.ConnectToTheVoiceChannelService;
import exceptions.SoundNotFoundException;
import lavaplayer.PlayerManager;
import music.SearchVideoOnYoutubeImpl;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import sendmessages.SendMessageImpl;
import sendmessages.SendMessageInterface;

import java.io.IOException;

public class PlayRandomCommand implements Command {
    private SendMessageInterface sender;
    private SearchVideoOnYoutubeImpl searcher;
    private GuildMessageReceivedEvent event;
    private ConnectToTheVoiceChannelService connect;

    public PlayRandomCommand(SendMessageInterface sender) {
        this.sender = sender;
        this.searcher = new SearchVideoOnYoutubeImpl();
        this.connect = new ConnectToTheVoiceChannelService();
    }
    @Override
    public void execute(GuildMessageReceivedEvent event) throws SoundNotFoundException {
        this.event = event;
        PlayerManager.getInstance()
                .getMusicManager(event.getGuild())
                .getScheduler().setEvent(event);
        connect.connectToTheVoiceChannel(event);
        try {
            play(searcher.searchRandomVideo());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void play(String trackUrl)  {
        PlayerManager.getInstance().loadAndPlay(event.getChannel(), trackUrl);
    }
}
