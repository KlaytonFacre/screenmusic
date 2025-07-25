package dev.klaytonfacre.screenmusic.services;

import dev.klaytonfacre.screenmusic.models.ArtistType;
import dev.klaytonfacre.screenmusic.models.MusicType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
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
        System.out.println("2. Consultar Biografia de Artista");
        System.out.println("3. Criar Música no Banco de Dados");
        System.out.println("4. Listar Músicas do Banco de Dados");

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
                searchArtistBioWorkflow();
                break;
            case 3:
                createMusicWorkflow();
                break;
            case 4:
                searchMusicWorkflow();
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private void createArtistWorkflow() {
        String creatingName = getSomeStringFromUser("Digite o nome do artista: ");
        String creatingType = getArtistTypeFromUser();
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
        var music = musicService.make(creatingMusicTitle, MusicType.fromString(creatingMusicType), creatingArtistName, creatingAlbumName);
        musicService.save(music);
        System.out.printf("Música criada com sucesso! ID: %s, Título: %s, Tipo: %s, Artista: %s\n", music.getId(), music.getTitle(), music.getType(), music.getArtist().getName());
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
        String creatingArtistName = getSomeStringFromUser("Digite o nome do artista: ");
        var album = albumService.make(creatingAlbumName);
        musicService.saveAlbum(album);
        System.out.printf("Álbum criado com sucesso! ID: %s, Nome: %s, Artista: %s\n", album.getId(), album.getName(), album.getArtist().getName());
    }

    private String getSomeStringFromUser(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private String getArtistTypeFromUser() {
        System.out.printf("Digite o tipo do artista %s: ", Arrays.toString(ArtistType.values()));
        String tipo = scanner.nextLine().toUpperCase();
        while (!ArtistType.isValidType(tipo)) {
            System.out.printf("Tipo inválido. Digite novamente %s: ", Arrays.toString(ArtistType.values()));
            tipo = scanner.nextLine().toUpperCase();
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
