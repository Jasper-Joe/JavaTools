package core.convert;

import core.convert.ConverterRegistry;

import java.lang.reflect.Type;

public class Convert {

    public static <T> T convert(Class<T> type, Object value) throws ConvertException {
        return convert((Type) type, value);
    }

    public static <T> T convert(Type type, Object value, T defaultValue) throws ConvertException {
        return convertWithCheck(type, value, defaultValue, false);
    }

    public static <T> T convert(Class<T> type, Object value, T defaultValue) throws ConvertException {
        return convert((Type) type, value, defaultValue);
    }

    public static String toStr(Object value) {
        return toStr(value, null);
    }

    public static <T> T convertWithCheck(Type type, Object value, T defaultValue, boolean quietly) {
        final ConverterRegistry registry = ConverterRegistry.getInstance();
        try {
            return registry.convert(type, value, defaultValue);
        } catch (Exception e) {
            if (quietly) {
                return defaultValue;
            }
            throw e;
        }
    }
}
