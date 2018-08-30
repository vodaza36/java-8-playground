package at.hochbichler.java8.lambdadesign;

import java.math.BigDecimal;
import java.util.function.Function;

public class CalculateNAV {
    private Function<String, BigDecimal> priceFinder;

    public CalculateNAV(final Function<String, BigDecimal> priceFinder) {
        this.priceFinder = priceFinder;
    }

    public BigDecimal computeStockWorth(final String ticker, final int shares) {
        return priceFinder.apply(ticker).multiply(BigDecimal.valueOf(shares));
    }
}
