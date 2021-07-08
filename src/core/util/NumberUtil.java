package core.util;

import java.math.BigDecimal;

public class NumberUtil {
    public static boolean equals(char c1, char c2, boolean ignoreCase) {
        return CharUtil.equals(c1, c2, ignoreCase);
    }
    public static boolean equals(BigDecimal bigNum1, BigDecimal bigNum2) {
        if (bigNum1 == bigNum2) {
            return true;
        }

        if (bigNum1 == null || bigNum2 == null) {
            return false;
        }
        return bigNum1.compareTo(bigNum2) == 0;
    }
}
