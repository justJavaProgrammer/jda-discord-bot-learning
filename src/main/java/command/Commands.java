package command;

public enum Commands {

    PLAY("/play"),
    QUEUE("/queue"),
    CLEAR("/clear"),
    STOP("/stop"),
    SKIP("/skip"),
    LEAVE("/leave"),
    PAUSE("/pause"),
    UNPAUSE("/unpause"),
    HELP("/help"),
    RANDOM("/random"),
    LYRICS("/lyrics");

    private String command;

    Commands(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
