package com.ejercicio1.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {
    private Long id;
    private String brand;
    private String cardNumber;
    private String expirationDate;
    private Double cardRate;

    public CardDTO(Long id, String cardNumber, String brand, String expirationDate) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.brand = brand;
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " - Marca: " + brand +
                " - Numero de tarjeta: " + cardNumber +
                " - Vencimiento: " + expirationDate;
    }
}
