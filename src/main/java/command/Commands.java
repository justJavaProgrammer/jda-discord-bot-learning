package command;

public enum Commands {
    PLAY("/play"),
    QUEUE("/queue"),
    CLEAR("/clear"),
    STOP("/stop"),
    LEAVE("/leave");

    private String command;

    Commands(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
