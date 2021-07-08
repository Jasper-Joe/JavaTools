package core.util;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class TypeUtil {
    public static boolean isUnknown(Type type) {
        return type == null || type instanceof TypeVariable;
    }
}
