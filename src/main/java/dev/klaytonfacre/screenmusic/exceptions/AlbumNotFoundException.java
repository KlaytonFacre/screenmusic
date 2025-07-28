package dev.klaytonfacre.screenmusic.exceptions;

public class AlbumNotFoundException extends IndexOutOfBoundsException {
    public AlbumNotFoundException() {
        super("Album not found in database");
    }
}
