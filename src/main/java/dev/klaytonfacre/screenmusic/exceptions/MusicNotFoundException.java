package dev.klaytonfacre.screenmusic.exceptions;

public class MusicNotFoundException extends IndexOutOfBoundsException {
    public MusicNotFoundException(String message) {
        super("Music not found in database");
    }
}
