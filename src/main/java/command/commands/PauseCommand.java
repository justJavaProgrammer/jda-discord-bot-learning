package command.commands;

import command.Command;
import exceptions.SoundNotFoundException;
import lavaplayer.PlayerManager;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import sendmessages.SendMessageInterface;

public class PauseCommand implements Command {
    private SendMessageInterface sender;

    public PauseCommand(SendMessageInterface sender) {
        this.sender = sender;
    }

    @Override
    public void execute(GuildMessageReceivedEvent event) throws SoundNotFoundException {
        event.getChannel().sendMessage("pause track ").queue();
        PlayerManager.getInstance().getMusicManager(event.getGuild()).getScheduler().pause();
    }
}
