package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    // Handson1 Quick Example - getAllCountries
    @Transactional
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    // Handson1 additional - findCountryByCode (Hands on 6)
    @Transactional
    public Country findCountryByCode(String countryCode) {
        Optional<Country> result = countryRepository.findById(countryCode);
        if (!result.isPresent()) {
            throw new RuntimeException("Country not found for code: " + countryCode);
        }
        Country country = result.get();
        return country;
    }

    // Handson1 additional - addCountry (Hands on 7)
    @Transactional
    public void addCountry(Country country) {
        countryRepository.save(country);
    }

    // Handson1 additional - updateCountry (Hands on 8)
    @Transactional
    public void updateCountry(String code, String name) {
        Optional<Country> result = countryRepository.findById(code);
        if (result.isPresent()) {
            Country country = result.get();
            country.setName(name);
            countryRepository.save(country);
        }
    }

    // Handson1 additional - deleteCountry (Hands on 9)
    @Transactional
    public void deleteCountry(String code) {
        countryRepository.deleteById(code);
    }

    // Handson2 - Query Methods
    @Transactional
    public List<Country> findCountriesContaining(String keyword) {
        return countryRepository.findByNameContaining(keyword);
    }

    @Transactional
    public List<Country> findCountriesContainingSorted(String keyword) {
        return countryRepository.findByNameContainingOrderByNameAsc(keyword);
    }

    @Transactional
    public List<Country> findCountriesStartingWith(String letter) {
        return countryRepository.findByNameStartingWith(letter);
    }
}
