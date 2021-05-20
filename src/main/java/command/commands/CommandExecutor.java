package command.commands;

import command.Command;
import exceptions.SoundNotFoundException;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class CommandExecutor {
    public void executeCommand(Command command, GuildMessageReceivedEvent event) throws SoundNotFoundException {
        command.execute(event);
    }
}
