package com.ejercicio1.service;

import com.ejercicio1.DTO.CardDTO;
import com.ejercicio1.Utils.Utils;
import com.ejercicio1.model.Card;
import com.ejercicio1.repository.CardRepository;
import com.ejercicio1.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class CardService {

    private Scanner inputScanner = new Scanner(System.in);
    private final Utils utils = new Utils();

    private final CardRepository cardRepository;
    private final PersonRepository personRepository;


    public CardService(CardRepository cardRepository, PersonRepository personRepository) {
        this.cardRepository = cardRepository;
        this.personRepository = personRepository;
    }


    public void getAllCardsFromDni() {
        System.out.println("------------------------------------------------");
        System.out.println("Para ver las tarjetas asociadas, ingrese el DNI, solo con numeros");
        System.out.println("------------------------------------------------");

        String dniInput = inputScanner.nextLine();
        while (!dniInput.matches(utils.dniRegex)) {
            System.out.println("DNI invalido - Ingrese solo numeros, hasta 8 caracteres");
            dniInput = inputScanner.nextLine();
        }
        Long parsedDniToLong = Long.parseLong(dniInput);
        List<Card> cardsList = cardRepository.findCardsByDni(parsedDniToLong);

        String cards = cardsList.stream()
                .map(card -> new CardDTO(card.getId(), card.getCardNumber(), card.getBrand(),
                        card.getExpirationDate().toString()))
                .toList().toString();

        System.out.println("Tarjetas asociadas: ");
        System.out.println(cards);

    }
}
