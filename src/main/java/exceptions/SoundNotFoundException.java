package exceptions;

public class SoundNotFoundException extends Exception {
    public SoundNotFoundException() {
    }

    public SoundNotFoundException(String message) {
        super(message);
    }
}
