package core.convert;

public interface Converter<T> {
    T convert(Object value, T defaultValue) throws IllegalArgumentException;
}
