package core.util;

import core.lang.Matcher;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayUtil extends PrimitiveArrayUtil{
    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(Object array) {
        if (array != null) {
            if (isArray(array)) {
                return Array.getLength(array) == 0;
            }
            return false;
        }
        return true;
    }

    public static boolean isNotEmpty(Object array) {
        return !isEmpty(array);
    }

    public static <T> boolean isNotEmpty(T[] array) {
        return array != null && array.length != 0;
    }

    public static boolean isArray(Object obj) {
        return obj != null && obj.getClass().isArray();
    }


    public static <T> int indexOf(T[] array, Object value) {
        return matchIndex((obj) -> ObjectUtil.equal(value, obj), array);
    }
    public static <T> int indexOf(T[] array, Object value, int beginIndexInclude) {
        return matchIndex((obj) -> ObjectUtil.equal(value, obj), beginIndexInclude, array);
    }

    public static <T> int matchIndex(Matcher<T> matcher, T... array) {
        return matchIndex(matcher, 0, array);
    }
    public static <T> int matchIndex(Matcher<T> matcher, int beginIndexInclude, T... array) {
        if (isNotEmpty(array)) {
            for (int i = beginIndexInclude; i < array.length; i++) {
                if (matcher.match(array[i])) {
                    return i;
                }
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static <T> boolean contains(T[] array, T value) {
        return indexOf(array, value) > INDEX_NOT_FOUND;
    }

    public static <T> boolean contains(char value, char... array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return true;
            }
        }
        return false;
    }

    public static String toString(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof long[]) {
            return Arrays.toString((long[]) obj);
        } else if (obj instanceof int[]) {
            return Arrays.toString((int[]) obj);
        } else if (obj instanceof short[]) {
            return Arrays.toString((short[]) obj);
        } else if (obj instanceof char[]) {
            return Arrays.toString((char[]) obj);
        } else if (obj instanceof byte[]) {
            return Arrays.toString((byte[]) obj);
        } else if (obj instanceof boolean[]) {
            return Arrays.toString((boolean[]) obj);
        } else if (obj instanceof float[]) {
            return Arrays.toString((float[]) obj);
        } else if (obj instanceof double[]) {
            return Arrays.toString((double[]) obj);
        } else if (ArrayUtil.isArray(obj)) {
            try {
                return Arrays.deepToString((Object[]) obj);
            } catch (Exception ignore) {

            }
        }

        return obj.toString();
    }
}
