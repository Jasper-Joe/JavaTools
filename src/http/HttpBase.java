package http;

import core.collection.CollectionUtil;
import core.map.MapUtil;
import core.util.StrUtil;

import java.util.*;

public abstract class HttpBase<T> {
    public static String HTTP_1_1 = "HTTP/1.1";
    protected String httpVersion = HTTP_1_1;
    protected Map<String, List<String>> headers= new HashMap<>();

    public T removeHeader(String name) {
        if (name != null) {
            headers.remove(name.trim());
        }
        return (T)this;
    }

    public T header(String name, String value, boolean isOverride) {
        if (name != null && value != null) {
            final List<String> values = headers.get(name.trim());
            if (isOverride || CollectionUtil.isEmpty(values)) {
                final ArrayList<String> valueList = new ArrayList<>();
                valueList.add(value);
                headers.put(name.trim(), valueList);
            } else {
                values.add(value.trim());
            }
        }
        return (T) this;
    }

    public T header(Header name, String value) {
        return header(name.toString(), value, true);
    }

    public T header(String name, String value) {
        return header(name, value, true);
    }

    public T header(Map<String, List<String>> headers, boolean isOverride) {
        if (MapUtil.isEmpty(headers)) {
            return (T) this;
        }
        String name;
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            name = entry.getKey();
            for (String value : entry.getValue()) {
                this.header(name, StrUtil.nullToEmpty(value), isOverride);
            }
        }
        return (T) this;
    }

    public Map<String, List<String>> headers() {
        return Collections.unmodifiableMap(headers);
    }

    public T removeHeader(Header name) {
        return removeHeader(name.toString());
    }

    public String httpVersion() {
        return httpVersion;
    }

    public T httpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
        return (T) this;
    }


//    public String header(String name) {
//
//    }
//
//    public List<String> headerList(String name) {
//        if (StrUtil.isBlank(name)) {
//            return null;
//        }
//        final CaseInsen
//    }

//    public String header(Header name) {
//        if (name == null) {
//            return null;
//        }
//        return header(name.toString());
//    }
}
