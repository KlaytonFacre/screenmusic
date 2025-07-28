package dev.klaytonfacre.screenmusic;

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
