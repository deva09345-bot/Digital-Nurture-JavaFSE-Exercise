package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    private static CountryService countryService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        countryService = context.getBean(CountryService.class);
        LOGGER.info("Inside main");

        // Handson1 - Quick Example
        testGetAllCountries();

        // Handson1 - Find by code (Hands on 6)
        testGetCountryByCode();

        // Handson1 - Add country (Hands on 7)
        testAddCountry();

        // Handson1 - Update country (Hands on 8)
        testUpdateCountry();

        // Handson1 - Delete country (Hands on 9)
        testDeleteCountry();

        // Handson2 - Query Methods
        testCountriesContaining();
        testCountriesContainingSorted();
        testCountriesStartingWith();
    }

    // Handson1 - Quick Example
    private static void testGetAllCountries() {
        LOGGER.info("Start");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.debug("countries={}", countries);
        LOGGER.info("End");
    }

    // Handson1 - Hands on 6
    private static void testGetCountryByCode() {
        LOGGER.info("Start");
        Country country = countryService.findCountryByCode("IN");
        LOGGER.debug("Country:{}", country);
        LOGGER.info("End");
    }

    // Handson1 - Hands on 7
    private static void testAddCountry() {
        LOGGER.info("Start");
        Country c = new Country();
        c.setCode("ZZ");
        c.setName("Test Country");
        countryService.addCountry(c);
        Country added = countryService.findCountryByCode("ZZ");
        LOGGER.debug("Added Country:{}", added);
        LOGGER.info("End");
    }

    // Handson1 - Hands on 8
    private static void testUpdateCountry() {
        LOGGER.info("Start");
        countryService.updateCountry("ZZ", "Updated Test Country");
        Country updated = countryService.findCountryByCode("ZZ");
        LOGGER.debug("Updated Country:{}", updated);
        LOGGER.info("End");
    }

    // Handson1 - Hands on 9
    private static void testDeleteCountry() {
        LOGGER.info("Start");
        countryService.deleteCountry("ZZ");
        LOGGER.debug("Deleted country ZZ");
        LOGGER.info("End");
    }

    // Handson2 - Query Methods
    private static void testCountriesContaining() {
        LOGGER.info("Start");
        List<Country> countries = countryService.findCountriesContaining("ou");
        LOGGER.debug("Countries containing 'ou':{}", countries);
        LOGGER.info("End");
    }

    private static void testCountriesContainingSorted() {
        LOGGER.info("Start");
        List<Country> countries = countryService.findCountriesContainingSorted("ou");
        LOGGER.debug("Countries containing 'ou' sorted:{}", countries);
        LOGGER.info("End");
    }

    private static void testCountriesStartingWith() {
        LOGGER.info("Start");
        List<Country> countries = countryService.findCountriesStartingWith("Z");
        LOGGER.debug("Countries starting with Z:{}", countries);
        LOGGER.info("End");
    }
}
