package command.commands;

import command.Command;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import sendmessages.SendMessageInterface;

public class UnknownCommand implements Command {

    private final String UNKNOWN_COMMAND_MESSAGE;
    private SendMessageInterface sendMessageInterface;

    public UnknownCommand(SendMessageInterface sendMessageInterface) {
        UNKNOWN_COMMAND_MESSAGE = "SORRY, BUT I DON'T KNOW THIS COMMAND";
        this.sendMessageInterface = sendMessageInterface;
    }

    public String getUNKNOWN_COMMAND_MESSAGE() {
        return UNKNOWN_COMMAND_MESSAGE;
    }


    @Override
    public void execute(GuildMessageReceivedEvent event) {

    }
}
