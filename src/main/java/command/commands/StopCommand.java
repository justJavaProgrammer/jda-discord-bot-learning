package command.commands;

import command.Command;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import sendmessages.SendMessageInterface;

public class StopCommand implements Command {
    private SendMessageInterface sender;

    public StopCommand(SendMessageInterface sender) {
        this.sender = sender;
    }
    @Override
    public void execute(GuildMessageReceivedEvent event) {
        this.sender.sendMessage(event, "Stop queue");
    }
}
