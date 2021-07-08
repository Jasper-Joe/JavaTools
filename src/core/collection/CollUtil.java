package core.collection;

import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

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

    public static <T> List<List<T>> group(Collection<T> collection, Hash32<T> hash) {
        final List<List<T>> result = new ArrayList<>();
        if (isEmpty(collection)) {
            return result;
        }
        if (null == hash) {
            // 默认hash算法，按照元素的hashCode分组
            hash = t -> (null == t) ? 0 : t.hashCode();
        }

        int index;
        List<T> subList;
        for (T t : collection) {
            index = hash.hash32(t);
            if (result.size() - 1 < index) {
                while (result.size() - 1 < index) {
                    result.add(null);
                }
                result.set(index, newArrayList(t));
            } else {
                subList = result.get(index);
                if (null == subList) {
                    result.set(index, newArrayList(t));
                } else {
                    subList.add(t);
                }
            }
        }
        return result;
    }


    public static boolean isNotEmpty(Iterable<?> iterable) {
        return IterUtil.isNotEmpty(iterable);
    }


}
