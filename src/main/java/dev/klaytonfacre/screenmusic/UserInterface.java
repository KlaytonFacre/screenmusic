package dev.klaytonfacre.screenmusic;

import dev.klaytonfacre.screenmusic.exceptions.AlbumNotFoundException;
import dev.klaytonfacre.screenmusic.exceptions.ArtistNotFoundException;
import dev.klaytonfacre.screenmusic.models.AlbumModel;
import dev.klaytonfacre.screenmusic.models.MusicModel;
import dev.klaytonfacre.screenmusic.models.types.ArtistType;
import dev.klaytonfacre.screenmusic.models.types.MusicType;
import dev.klaytonfacre.screenmusic.services.AlbumService;
import dev.klaytonfacre.screenmusic.services.ArtistService;
import dev.klaytonfacre.screenmusic.services.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

@Component
public class UserInterface {
    @Autowired
    ArtistService artistService;
    @Autowired
    MusicService musicService;
    @Autowired
    AlbumService albumService;
    Scanner scanner = new Scanner(System.in);

    public UserInterface() {
        // Default constructor for Spring
    }

    public void loop() {
        while (true) {
            try {
                showMenuOptions();
                int option = promptUserForInput("Escolha uma opção: ");
                optionManager(option);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
            }
        }
    }

    private void showMenuOptions() {
        System.out.println("Bem-vindo ao ScreenMusic!");
        System.out.println("1. Criar Artista no Banco de Dados");
        System.out.println("2. Criar Álbum no Banco de Dados");
        System.out.println("3. Consultar Biografia de Artista");
        System.out.println("4. Criar Música no Banco de Dados");
        System.out.println("5. Buscar Músicas do Banco de Dados");
        System.out.println("6. Buscar Álbum do Banco de Dados");

        System.out.println("0. Sair");
    }

    private int promptUserForInput(String prompt) throws NumberFormatException {
        System.out.print(prompt);
        return Integer.parseInt(scanner.nextLine());
    }

    private void optionManager(int option) {
        switch (option) {
            case 0:
                System.out.println("Saindo...");
                System.exit(0);
                break;
            case 1:
                createArtistWorkflow();
                break;
            case 2:
                createAlbumWorkflow();
                break;
            case 3:
                searchArtistBioWorkflow();
                break;
            case 4:
                createMusicWorkflow();
                break;
            case 5:
                searchMusicWorkflow();
                break;
            case 6:
                searchAlbumWorkflow();
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private void createArtistWorkflow() {
        String creatingName = getSomeStringFromUser("Digite o nome do artista: ");
        String creatingType = getValidArtistTypeFromUser();
        var artist = artistService.make(creatingName, ArtistType.fromString(creatingType));
        artistService.save(artist);
        System.out.printf("Artista criado com sucesso! ID: %s, %s (%s)\n", artist.getId(), artist.getName(), artist.getType());
    }

    private void searchArtistBioWorkflow() {
        String searchingName = getSomeStringFromUser("Digite o nome do artista: ");
        var artists = artistService.searchByName(searchingName);
        if (artists.isEmpty()) {
            System.out.println("Nenhum artista encontrado com esse nome.");
        } else {
            System.out.println("Artistas encontrados:");
            artists.forEach(System.out::println);
        }
    }

    private void createMusicWorkflow() {
        String creatingMusicTitle = getSomeStringFromUser("Digite o título da música: ");
        String creatingMusicType = getMusicTypeFromUser();
        String creatingArtistName = getSomeStringFromUser("Digite o nome do artista: ");
        String creatingAlbumName = getSomeStringFromUser("Digite o nome do álbum (opcional, pressione Enter para pular): ");
        try {
            var music = musicService.make(creatingMusicTitle, MusicType.fromString(creatingMusicType), creatingArtistName, creatingAlbumName);
            musicService.save(music);
            System.out.printf("Música criada com sucesso! ID: %s, Título: %s, Tipo: %s, Artista: %s\n", music.getId(), music.getTitle(), music.getType(), music.getArtist().getName());
        } catch (ArtistNotFoundException | AlbumNotFoundException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void searchMusicWorkflow() {
        String searchMusicTitle = getSomeStringFromUser("Digite o título da música para buscar: ");
        var musics = musicService.searchByName(searchMusicTitle);
        if (musics.isEmpty()) {
            System.out.println("Nenhuma música encontrada com esse título.");
        } else {
            System.out.println("Músicas encontradas:");
            musics.forEach(System.out::println);
        }
    }

    private void createAlbumWorkflow() {
        String creatingAlbumName = getSomeStringFromUser("Digite o nome do álbum: ");
        var album = albumService.make(creatingAlbumName);
        albumService.save(album);
        System.out.printf("Álbum criado com sucesso! ID: %s, Nome: %s, Capa: %s\n", album.getId(), album.getName(), album.getCoverUrl());
    }

    private void searchAlbumWorkflow() {
        String searchAlbumName = getSomeStringFromUser("Digite o nome do álbum para buscar: ");
        var musics = musicService.searchByAlbum(searchAlbumName).stream()
                .sorted(Comparator.comparing(m -> m.getAlbum().getId()))
                .toList();
        if (musics.isEmpty()) {
            System.out.println("Nenhum álbum encontrado com esse nome.");
        } else {
            Long lastAlbumId = null;
            for (MusicModel musicModel : musics) {
                AlbumModel albumModel = musicModel.getAlbum();
                if (!albumModel.getId().equals(lastAlbumId)) {
                    System.out.printf("Músicas do Álbum %s:%n", albumModel.getName());
                    lastAlbumId = albumModel.getId();
                }
                System.out.println(musicModel);
            }
        }
    }

    private String getSomeStringFromUser(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private String getValidArtistTypeFromUser() {
        String prompt = "Digite o tipo do artista %s: ".formatted(Arrays.toString(ArtistType.values()));
        String tipo = getSomeStringFromUser(prompt).toUpperCase();
        while (!ArtistType.isValidType(tipo)) {
            tipo = getSomeStringFromUser("Tipo inválido. Digite novamente %s: ".formatted(Arrays.toString(ArtistType.values()))).toUpperCase();
        }
        return tipo;
    }

    private String getMusicTypeFromUser() {
        System.out.printf("Digite o tipo da música %s: ", Arrays.toString(MusicType.values()));
        String tipo = scanner.nextLine().toUpperCase();
        while (!MusicType.isValidType(tipo)) {
            System.out.printf("Tipo inválido. Digite novamente %s: ", Arrays.toString(MusicType.values()));
            tipo = scanner.nextLine().toUpperCase();
        }
        return tipo;
    }
}
