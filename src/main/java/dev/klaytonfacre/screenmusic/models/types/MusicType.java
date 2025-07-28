package dev.klaytonfacre.screenmusic.models.types;

public enum MusicType {
    ROCK("ROCK"),
    SAMBA("SAMBA"),
    JAZZ("JAZZ"),
    POP("POP"),
    CLASSICA("CLASSICA"),
    MPB("MPB"),
    FORRO("FORRO"),
    REGGAE("REGGAE"),
    HIPHOP("HIPHOP"),
    FUNK("FUNK"),
    ELETRONICA("ELETRONICA"),
    BLUES("BLUES"),
    COUNTRY("COUNTRY"),
    PAGODE("PAGODE");

    private String type;

    MusicType(String type) {
        this.type = type;
    }

    public static MusicType fromString(String text) {
        for (MusicType musicType : MusicType.values()) {
            if (musicType.type.equalsIgnoreCase(text)) {
                return musicType;
            }
        }
        throw new IllegalArgumentException("Nenhum tipo de musica encontrado para este texto: " + text);
    }

    public static boolean isValidType(String tipo) {
        for (MusicType musicType : MusicType.values()) {
            if (musicType.type.equalsIgnoreCase(tipo)) {
                return true;
            }
        }
        return false;
    }
}
