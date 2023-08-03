import org.example.TipService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;



import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TipServiceTest {

    private final TipService tipService = new TipService();

    @Test
    void testRoundTipsWithAmountLessThanBoundary() {
        BigDecimal amount = BigDecimal.valueOf(500);
        BigDecimal expected = amount.multiply(BigDecimal.valueOf(1.1)); // 10% tip
        BigDecimal result = tipService.roundTips(amount);
        assertEquals(expected, result);
    }

    @Test
    void testRoundTipsWithAmountEqualToBoundary() {
        BigDecimal amount = BigDecimal.valueOf(1000);
        BigDecimal expected = amount.multiply(BigDecimal.valueOf(1.05)); // 5% tip
        BigDecimal result = tipService.roundTips(amount);
        assertEquals(expected, result);
    }

    @Test
    void testRoundTipsWithAmountGreaterThanBoundary() {
        BigDecimal amount = BigDecimal.valueOf(1500);
        BigDecimal expected = amount.multiply(BigDecimal.valueOf(1.05)); // 5% tip
        BigDecimal result = tipService.roundTips(amount);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "800, 880",   // 10% tip when amount < 1000
            "1200, 1260", // 5% tip when amount >= 1000
            "2000, 2100"  // 5% tip when amount >= 1000
    })
    void testRoundTipsWithParameterizedCases(BigDecimal amount, BigDecimal expected) {
        BigDecimal result = tipService.roundTips(amount);
        assertEquals(

                0, expected.compareTo(result));
    }
}