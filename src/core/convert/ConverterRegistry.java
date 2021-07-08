package core.convert;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Map;

public class ConverterRegistry implements Serializable {
    private Map<Type, Converter<?>> defaultConverterMap;
    private volatile Map<Type, Converter<?>> customConverterMap;

    private static class SingletonHolder {
        private static final ConverterRegistry INSTANCE = new ConverterRegistry();
    }
    public static ConverterRegistry getInstance() {
        return SingletonHolder.INSTANCE;
    }

}
