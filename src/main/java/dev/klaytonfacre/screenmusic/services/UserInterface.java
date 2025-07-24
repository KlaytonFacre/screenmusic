package dev.klaytonfacre.screenmusic.services;

import dev.klaytonfacre.screenmusic.models.ArtistType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserInterface {
    @Autowired
    ArtistService artistService;
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
                String creatingName = getArtistNameFromUser();
                String creatingType = getArtistTypeFromUser();
                var artist = artistService.make(creatingName, ArtistType.fromString(creatingType));
                artistService.save(artist);
                System.out.printf("Artista criado com sucesso! ID: %s, %s (%s)\n", artist.getId(), artist.getName(), artist.getType());
                break;
            case 2:
                String searchingName = getArtistNameFromUser();
                var artists = artistService.searchByName(searchingName);
                System.out.println("Artistas encontrados:");
                artists.forEach(System.out::println);
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private String getArtistNameFromUser() {
        System.out.print("Digite o nome do artista: ");
        return scanner.nextLine();
    }

    private String getArtistTypeFromUser() {
        System.out.print("Digite o tipo do artista (SOLO, DUPLA, BANDA): ");
        String tipo = scanner.nextLine().toUpperCase();
        while (!ArtistType.isValidType(tipo)) {
            System.out.print("Tipo inválido. Digite novamente (SOLO, DUPLA, BANDA): ");
            tipo = scanner.nextLine().toUpperCase();
        }
        return tipo;
    }
}
