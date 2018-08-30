package at.hochbichler.java8.lambdadesign;

import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.util.function.Function;

class CalculateNAVTest {

    private CalculateNAV calculateNAV;
    private Function<String, BigDecimal> priceFinder;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        this.priceFinder = ticker -> BigDecimal.valueOf(6.01);
        this.calculateNAV = new CalculateNAV(priceFinder);
    }

    @org.junit.jupiter.api.Test
    void computeStockWorth() {
        // Given
        int shares = 1000;
        String ticker = "GOOGL";

        // When
        BigDecimal stockWorth = this.calculateNAV.computeStockWorth(ticker, shares);

        // Then
        BigDecimal expected = BigDecimal.valueOf(6010.00);
        Assertions.assertEquals(0, stockWorth.compareTo(expected));
    }
}