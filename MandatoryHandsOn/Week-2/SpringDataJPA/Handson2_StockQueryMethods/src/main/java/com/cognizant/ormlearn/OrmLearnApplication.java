package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Stock;
import com.cognizant.ormlearn.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    private static StockService stockService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        stockService = context.getBean(StockService.class);
        LOGGER.info("Inside main");

        testFBStocksSeptember2019();
        testGoogleStocksAbove1250();
        testTop3HighestVolumeStocks();
    }

    private static void testFBStocksSeptember2019() {
        LOGGER.info("Start");
        List<Stock> stocks = stockService.getFBStocksInSeptember2019();
        LOGGER.debug("FB stocks in Sep 2019:{}", stocks);
        LOGGER.info("End");
    }

    private static void testGoogleStocksAbove1250() {
        LOGGER.info("Start");
        List<Stock> stocks = stockService.getGoogleStocksAbove1250();
        LOGGER.debug("GOOGL stocks above 1250:{}", stocks);
        LOGGER.info("End");
    }

    private static void testTop3HighestVolumeStocks() {
        LOGGER.info("Start");
        List<Stock> stocks = stockService.getTop3HighestVolumeStocks();
        LOGGER.debug("Top 3 volume stocks:{}", stocks);
        LOGGER.info("End");
    }
}
