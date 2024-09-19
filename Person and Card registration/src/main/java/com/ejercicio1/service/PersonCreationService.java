package com.ejercicio1.service;

import com.ejercicio1.Utils.Utils;
import com.ejercicio1.model.Person;
import com.ejercicio1.repository.PersonRepository;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.stereotype.Service;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PersonCreationService {

    private final Utils utils = new Utils();
    private final PersonRepository personRepository;
    private Scanner inputScanner = new Scanner(System.in);
    private boolean continueToMenu = false;

    public PersonCreationService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void writePersonInformation() {

        while (!continueToMenu) {
            Pattern birthdatePattern = Pattern.compile(utils.dateRegex);

            String emailRegex = utils.emailRegex;
            Pattern emailPattern = Pattern.compile(emailRegex);

            System.out.println("------------------------------------------------");
            System.out.println("Para registrarse, ingrese los siguientes datos");
            System.out.println("------------------------------------------------");

            System.out.println("Nombre: ");
            String nameInput = inputScanner.nextLine();
            String capitalizedName = WordUtils.capitalizeFully(nameInput);

            System.out.println("Apellido: ");
            String lastNameInput = inputScanner.nextLine();
            String capitalizedLastname = WordUtils.capitalizeFully(lastNameInput);

            System.out.println("DNI - Solo numeros: ");
            String dniInput = inputScanner.nextLine();
            while (!dniInput.matches(utils.dniRegex)) {
                System.out.println("DNI invalido - Ingrese solo numeros, hasta 8 caracteres");
                dniInput = inputScanner.nextLine();
            }
            Long parsedDniToLong = Long.parseLong(dniInput);

            System.out.println("Fecha de nacimiento, con formato 01/01/2000: ");
            String birthdateInput = inputScanner.nextLine();
            Matcher expirationMatcher = birthdatePattern.matcher(birthdateInput);

            while (!expirationMatcher.matches()) {
                System.out.println("Fecha invalida, ingrese la fecha en formato 01/01/2000: ");
                birthdateInput = inputScanner.nextLine();

                System.out.println("Email: ");
                String emailInput = inputScanner.nextLine();
                while (!emailInput.matches(utils.emailRegex)) {
                    System.out.println("Email invalido, ingrese de nuevo");
                    emailInput = inputScanner.nextLine();
                }

                Person person = Person.builder()
                        .name(nameInput)
                        .lastName(lastNameInput)
                        .birthDate(birthdateInput)
                        .DNI(parsedDniToLong)
                        .email(emailInput)
                        .build();

                System.out.println("Datos ingresados:");
                System.out.println(capitalizedName + " - " + capitalizedLastname + " - " +
                        birthdateInput + " - " + parsedDniToLong + " - " + emailInput);
                personRepository.save(person);
                continueToMenu = true;
            }
        }
    }
}