package command.commands;

import command.Command;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import sendmessages.SendMessageInterface;

public class QueueCommand implements Command {
    private SendMessageInterface sender;

    public QueueCommand(SendMessageInterface sender) {
        this.sender = sender;
    }
    @Override
    public void execute(GuildMessageReceivedEvent event) {
        this.sender.sendMessage(event, "the queue of songs is ");
    }
}
