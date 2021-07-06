package core.collection;

import java.util.Collection;
import java.util.Enumeration;

public class CollUtil {
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Iterable<?> iterable) {
        return IterUtil.isEmpty(iterable);
    }
    public static boolean isEmpty(Enumeration<?> enumeration) {
        return enumeration == null || !enumeration.hasMoreElements();
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }
    public static boolean isNotEmpty (Enumeration<?> enumeration) {
        return enumeration != null && enumeration.hasMoreElements();
    }

    public static boolean isNotEmpty(Iterable<?> iterable) {
        return IterUtil.isNotEmpty(iterable);
    }


}
