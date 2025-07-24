package dev.klaytonfacre.screenmusic;

import dev.klaytonfacre.screenmusic.models.ArtistType;
import dev.klaytonfacre.screenmusic.repositories.ArtistRepository;
import dev.klaytonfacre.screenmusic.services.ConsultaChatGPT;
import dev.klaytonfacre.screenmusic.services.ArtistService;
import dev.klaytonfacre.screenmusic.services.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmusicApplication implements CommandLineRunner {
	@Autowired
	UserInterface userInterface;

	public static void main(String[] args) {
		SpringApplication.run(ScreenmusicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userInterface.loop();
	}
}
