package command.commands;

import command.Command;
import exceptions.SoundNotFoundException;
import lavaplayer.PlayerManager;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import sendmessages.SendMessageInterface;

public class UnpauseCommand implements Command {
    private SendMessageInterface sender;

    public UnpauseCommand(SendMessageInterface sender) {
        this.sender = sender;
    }

    @Override
    public void execute(GuildMessageReceivedEvent event) throws SoundNotFoundException {
        event.getChannel().sendMessage("Unpause current track").queue();
        PlayerManager.getInstance().getMusicManager(event.getGuild()).getScheduler().unpause();
    }
}
