package com.ejercicio1.service;

import com.ejercicio1.DTO.RateDTO;
import com.ejercicio1.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class CardRateService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    private Scanner inputScanner = new Scanner(System.in);
    private boolean continueToMenu = false;

    public void enterDateForCardRates(){
        while(!continueToMenu) {
            System.out.println("------------------------------------------------");
            System.out.println("Para consultar la tasa de todas las marcas, ingrese la fecha");
            System.out.println("------------------------------------------------");

            String dateInput = inputScanner.nextLine();

            LocalDate dateParsedToLocalDate;
            if(dateInput.trim().isEmpty()){
                dateParsedToLocalDate = LocalDate.now();
            } else {
                dateParsedToLocalDate = LocalDate.parse(dateInput, FORMATTER);
            }

            System.out.println("Resultados de tasas para la fecha " + dateInput + ":");
            getRatesByDate(dateParsedToLocalDate);

            continueToMenu = true;
        }
    }

    public RateDTO getRatesByDate(LocalDate date){
        double visaRate = date.getYear() / (double) date.getMonthValue();
        double naraRate = date.getDayOfMonth() * 0.5;
        double amexRate = date.getMonthValue() * 0.1;

        System.out.printf("VISA: %.2f%n", visaRate);
        System.out.printf("NARA: %.2f%n", naraRate);
        System.out.printf("AMEX: %.2f%n", amexRate);

        return new RateDTO(date, visaRate, naraRate, amexRate);
    }

}
