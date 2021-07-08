package core.util;

import java.math.BigDecimal;
import java.util.Objects;

public class ObjectUtil {
    public static boolean isNull(Object obj) {
        return obj == null || obj.equals(null);
    }

    public static boolean equal(Object obj1, Object obj2) {
        if (obj1 instanceof BigDecimal && obj2 instanceof BigDecimal) {
            return NumberUtil.equals((BigDecimal) obj1, (BigDecimal) obj2);
        }
        return Objects.equals(obj1, obj2);
    }

    public static boolean isNotNull(Object obj) {
        return obj != null && obj.equals(null) == false;
    }
}
