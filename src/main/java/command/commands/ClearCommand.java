package command.commands;

import command.Command;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import sendmessages.SendMessageInterface;

public class ClearCommand implements Command {
    private SendMessageInterface sendMessageInterface;

    public ClearCommand(SendMessageInterface sendMessageInterface) {
        this.sendMessageInterface = sendMessageInterface;
    }
    @Override
    public void execute(GuildMessageReceivedEvent event) {

    }
}
