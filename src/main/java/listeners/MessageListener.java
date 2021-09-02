package listeners;

import command.Command;
import command.commands.CommandContainer;
import command.commands.CommandExecutor;
import command.commands.HelpCommand;
import exceptions.CommandNotFoundException;
import exceptions.SoundNotFoundException;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import sendmessages.SendMessageInterface;

public class MessageListener extends ListenerAdapter {

    private final String COMMAND_PREFIX = "/";
    private static String message;
    private SendMessageInterface sender;
    private Command command;
    private CommandExecutor executor;

    public MessageListener(SendMessageInterface sender) {
        this.sender = sender;
        this.executor = new CommandExecutor();
    }

    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        this.message = event.getMessage().getContentRaw();
        try {
            onGuildMessageReceived0(event);
        } catch (SoundNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void onGuildMessageReceived0(GuildMessageReceivedEvent event) throws SoundNotFoundException {
        CommandContainer container = new CommandContainer(sender);
        if (getMessage().startsWith(COMMAND_PREFIX) && !event.getAuthor().isBot()) {
            try {
                command = container.getRetrieveCommand(message);
                executor.executeCommand(command, event);
            } catch (CommandNotFoundException e) {
                HelpCommand helpCommand = new HelpCommand(sender);
                helpCommand.execute(event);
            }
        }
    }

    public static String getMessage() {
        return message;
    }
}