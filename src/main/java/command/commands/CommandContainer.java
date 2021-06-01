package command.commands;

import command.Command;
import command.Commands;
import exceptions.CommandNotFoundException;
import sendmessages.SendMessageInterface;

import java.util.HashMap;

public class CommandContainer {

    private HashMap<String, Command> commandsMap;

    public CommandContainer(SendMessageInterface sendMessageInterface) {
        this.commandsMap = new HashMap<>();
        commandsMap.put(Commands.PLAY.getCommand(), new PlayCommand(sendMessageInterface));
        commandsMap.put(Commands.STOP.getCommand(), new StopCommand(sendMessageInterface));
        commandsMap.put(Commands.CLEAR.getCommand(), new ClearCommand(sendMessageInterface));
        commandsMap.put(Commands.LEAVE.getCommand(), new LeaveCommand(sendMessageInterface));
        commandsMap.put(Commands.SKIP.getCommand(), new SkipCommand(sendMessageInterface));
        commandsMap.put(Commands.PAUSE.getCommand(), new PauseCommand(sendMessageInterface));
        commandsMap.put(Commands.UNPAUSE.getCommand(), new UnpauseCommand(sendMessageInterface));
        commandsMap.put(Commands.HELP.getCommand(), new HelpCommand(sendMessageInterface));
        commandsMap.put(Commands.RANDOM.getCommand(), new PlayRandomCommand(sendMessageInterface));
        commandsMap.put(Commands.LYRICS.getCommand(), new SearchLyricsCommand(sendMessageInterface));
    }

    public Command getRetrieveCommand(String commandIdentifier) throws CommandNotFoundException {
        Command command = commandsMap.get(commandIdentifier.split(" ")[0]);
        if (command != null)
            return command;
        throw new CommandNotFoundException();
    }
}
