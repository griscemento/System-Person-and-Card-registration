package com.ejercicio1.service;

import com.ejercicio1.Utils.Utils;
import com.ejercicio1.model.Card;
import com.ejercicio1.model.Person;
import com.ejercicio1.repository.CardRepository;
import com.ejercicio1.repository.PersonRepository;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CardCreationService {

    private final Utils utils = new Utils();
    private final CardRepository cardRepository;
    private final PersonRepository personRepository;

    private final Pattern expirationDatePattern = Pattern.compile(utils.dateRegex);

    public CardCreationService(CardRepository cardRepository, PersonRepository personRepository) {
        this.cardRepository = cardRepository;
        this.personRepository = personRepository;
    }

    Scanner inputScanner = new Scanner(System.in);
    boolean continueToMenu = false;

    public void enterCardInformation(){
        while(!continueToMenu) {
            System.out.println("------------------------------------------------");
            System.out.println("Para registrar una tarjeta, ingrese los siguientes datos");
            System.out.println("------------------------------------------------");

            System.out.println("Marca de la tarjeta");
            String brandInput = inputScanner.nextLine();
            String capitalizedBrand = WordUtils.capitalizeFully(brandInput);

            System.out.println("Numero de la tarjeta");
            String cardNumberInput = inputScanner.nextLine();
            while (!cardNumberInput.matches(utils.cardNumberRegex)) {
                System.out.println("Ingreso invalido - Ingrese solo numeros, hasta 16 caracteres");
                cardNumberInput = inputScanner.nextLine();
            }

            System.out.println("Fecha de vencimiento: ");
            String expirationDateInput = inputScanner.nextLine();
            Matcher expirationMatcher = expirationDatePattern.matcher(expirationDateInput);
            while (!expirationMatcher.matches()) {
                System.out.println("Fecha invalida, ingrese la fecha en formato 01/01/2000: ");
                expirationDateInput = inputScanner.nextLine();
            }

            System.out.println("Nombre y Apellido: ");
            String fullNameInput = inputScanner.nextLine();
            String capitalizedFullName = WordUtils.capitalizeFully(fullNameInput);


            System.out.println("DNI, solo numeros: ");
            Long personDNI = inputScanner.nextLong();


            System.out.println("Verifique los datos ingresados:");
            System.out.println(capitalizedBrand + " - " + cardNumberInput + " - " +
                    expirationDateInput + " - " + capitalizedFullName);


            Person person = personRepository.findById(personDNI).orElseThrow(()
                    -> new NoSuchElementException("Person not found"));

            Card newCard = createCardForPerson(person.getDNI(), cardNumberInput, capitalizedBrand, expirationDateInput);
            cardRepository.save(newCard);
            continueToMenu = true;
        }
    }

    private Card createCardForPerson(Long personDNI, String cardNumber,
                                     String brand, String expirationDate){

        Person person = personRepository.findById(personDNI).orElseThrow(()
                -> new NoSuchElementException("Person not found"));

        return Card.builder()
                .brand(brand)
                .cardNumber(cardNumber)
                .expirationDate(expirationDate)
                .person(person)
                .build();
    }
}
