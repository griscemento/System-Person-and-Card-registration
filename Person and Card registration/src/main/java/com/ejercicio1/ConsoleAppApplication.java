package com.ejercicio1;

import com.ejercicio1.menu.MainMenu;
import com.ejercicio1.service.CardCreationService;
import com.ejercicio1.service.PersonCreationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsoleAppApplication implements CommandLineRunner {

	private final MainMenu mainMenu;

	public ConsoleAppApplication(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public static void main(String[] args) {
		SpringApplication.run(ConsoleAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mainMenu.menuSelection();
	}
}
