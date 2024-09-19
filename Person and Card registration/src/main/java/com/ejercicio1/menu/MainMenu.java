package com.ejercicio1.menu;

import com.ejercicio1.Utils.Utils;
import com.ejercicio1.service.CardCreationService;
import com.ejercicio1.service.CardRateService;
import com.ejercicio1.service.CardService;
import com.ejercicio1.service.PersonCreationService;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class MainMenu {

    private final Utils utils = new Utils();
    private final PersonCreationService personInformation;
    private final CardCreationService cardInformation;
    private final CardService cardService;
    private final CardRateService cardRateService;


    Scanner scanner = new Scanner(System.in);
    boolean isRunning = true;

    public MainMenu(PersonCreationService personInformation, CardCreationService cardInformation, CardService cardService, CardRateService cardRateService) {
        this.personInformation = personInformation;
        this.cardInformation = cardInformation;
        this.cardService = cardService;
        this.cardRateService = cardRateService;
    }


    public void menuSelection() {
        System.out.println("------------------------------------------------");
        System.out.println("------------------------------------------------");
        System.out.println("¡Bienvenido!");
        System.out.println("------------------------------------------------");
        System.out.println("------------------------------------------------");
        while (isRunning) {
            mainMenuOptions();

            String optionInput = scanner.nextLine();

            if (optionInput.matches(utils.optionRegex)) {
                int parsedInputToInt = Integer.parseInt(optionInput);
                if (parsedInputToInt >= 1 && parsedInputToInt <= 5) {

                    selectOption(parsedInputToInt);

                    System.out.println("Quiere realizar otra operacion? S para otra eleccion, N para salir del menu");
                    String continueInput = scanner.nextLine();
                    if(continueInput.equalsIgnoreCase("n")){
                        isRunning = false;
                    }
                } else {
                    System.out.println("Seleccion invalida, solo ingrese un numero del 1 al 5");
                }
            } else {
                System.out.println("Seleccion invalida, solo ingrese un numero");
            }
        }
        closeProgram();
    }

    public void selectOption(int option){
        switch (option) {
            case 1: personInformation.writePersonInformation();
            break;
            case 2: cardInformation.enterCardInformation();
            break;
            case 3: cardService.getAllCardsFromDni();
            break;
            case 4: cardRateService.enterDateForCardRates();
            break;
            case 5: closeProgram();
            break;
            default:
                System.out.println("Por favor seleccione una de las opciones descriptas");
        }
    }

    private void mainMenuOptions(){
        System.out.println("Seleccione la operacion deseada con su numero:");
        System.out.println("1 - Registrar a una persona");
        System.out.println("2 - Registrar una tarjeta");
        System.out.println("3 - Informacion de tarjeta segun DNI");
        System.out.println("4 - Consultar tasas por fecha");
        System.out.println("5 - Salir del programa");
    }

    private void closeProgram(){
        System.out.println("Ha salido del programa");
        scanner.close();
        System.exit(0);
    }
}
