package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    // Handson2 - Query Methods: find countries containing keyword (any position)
    List<Country> findByNameContaining(String keyword);

    // Handson2 - Query Methods: find containing + sorted ascending by name
    List<Country> findByNameContainingOrderByNameAsc(String keyword);

    // Handson2 - Query Methods: find countries starting with a letter
    List<Country> findByNameStartingWith(String letter);
}
