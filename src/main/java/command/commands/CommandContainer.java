package command.commands;

import command.Command;
import command.Commands;
import sendmessages.SendMessageInterface;

import java.util.HashMap;

public class CommandContainer  {

    private HashMap<String, Command> commandsMap;

    public CommandContainer(SendMessageInterface sendMessageInterface) {
        this.commandsMap = new HashMap<>();
        commandsMap.put(Commands.PLAY.getCommand(), new PlayCommand(sendMessageInterface));
        commandsMap.put(Commands.QUEUE.getCommand(), new QueueCommand(sendMessageInterface));
        commandsMap.put(Commands.STOP.getCommand(), new StopCommand(sendMessageInterface));
        commandsMap.put(Commands.CLEAR.getCommand(), new ClearCommand(sendMessageInterface));
        commandsMap.put(Commands.LEAVE.getCommand(), new LeaveCommand());
    }

    public Command getRetrieveCommand(String commandIdentifier) {
        Command command = commandsMap.get(commandIdentifier.split(" ")[0]);
        return command;
    }
}
