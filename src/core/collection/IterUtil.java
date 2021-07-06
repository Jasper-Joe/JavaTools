package core.collection;

import java.util.Iterator;

public class IterUtil {
    public static boolean isEmpty(Iterable<?> iterable) {
        return iterable == null || isEmpty(iterable.iterator());
    }

    public static boolean isEmpty(Iterator<?> Iterator) {
        return Iterator == null || !Iterator.hasNext();
    }

    public static boolean isNotEmpty(Iterable<?> iterable) {
        return iterable != null && isNotEmpty(iterable.iterator());
    }

    public static boolean isNotEmpty(Iterator<?> Iterator) {
        return Iterator != null && Iterator.hasNext();
    }
}
