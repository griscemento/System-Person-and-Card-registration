package com.ejercicio1.repository;

import com.ejercicio1.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    @Query("SELECT c FROM Card c WHERE c.person.DNI = :dni")
    List<Card> findCardsByDni(Long dni);

}
