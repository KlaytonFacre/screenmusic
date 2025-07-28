package dev.klaytonfacre.screenmusic.exceptions;

public class ArtistNotFoundException extends IndexOutOfBoundsException {
    public ArtistNotFoundException() {
        super("Artist not found in database");
    }
}
