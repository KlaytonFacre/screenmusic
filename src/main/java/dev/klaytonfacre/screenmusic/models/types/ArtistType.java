package dev.klaytonfacre.screenmusic.models.types;

public enum ArtistType {
    SOLO("SOLO"),
    DUPLA("DUPLA"),
    BANDA("BANDA");

    private String type;

    ArtistType(String type) {
        this.type = type;
    }

    public static ArtistType fromString(String text) {
        for (ArtistType artistType : ArtistType.values()) {
            if (artistType.type.equalsIgnoreCase(text)) {
                return artistType;
            }
        }
        throw new IllegalArgumentException("Nenhum tipo de artista encontrado para este texto: " + text);
    }

    public static boolean isValidType(String tipo) {
        for (ArtistType artistType : ArtistType.values()) {
            if (artistType.type.equalsIgnoreCase(tipo)) {
                return true;
            }
        }
        return false;
    }
}
