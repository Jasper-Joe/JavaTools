package core.util;

public class ObjectUtil {
    public static boolean isNull(Object obj) {
        return obj == null || obj.equals(null);
    }

    public static boolean isNotNull(Object obj) {
        return obj != null && obj.equals(null) == false;
    }
}
