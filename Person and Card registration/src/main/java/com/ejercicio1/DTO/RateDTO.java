package com.ejercicio1.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateDTO {
    private LocalDate date;
    private double visaRate;
    private double naraRate;
    private double amexRate;

}
