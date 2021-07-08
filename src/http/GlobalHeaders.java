package http;

import core.collection.CollectionUtil;
import core.map.MapUtil;
import core.util.StrUtil;

import java.util.*;

public enum GlobalHeaders {
    INSTANCE;
    protected Map<String, List<String>> headers = new HashMap<>();
    GlobalHeaders() {
        putDefault(false);
    }

    public GlobalHeaders putDefault(boolean isReset) {
        // 解决HttpURLConnection中无法自定义Host等头信息的问题
        // https://stackoverflow.com/questions/9096987/how-to-overwrite-http-header-host-in-a-httpurlconnection/9098440
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");

        if (isReset) {
            this.headers.clear();
        }

        header(Header.ACCEPT, "text/html,application/json,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8", true);
        header(Header.ACCEPT_ENCODING, "gzip, deflate", true);
        header(Header.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.8", true);
        // 此Header只有在post请求中有用，因此在HttpRequest的method方法中设置此头信息，此处去掉
        // header(Header.CONTENT_TYPE, ContentType.FORM_URLENCODED.toString(CharsetUtil.CHARSET_UTF_8), true);
        header(Header.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36 Hutool", true);
        return this;
    }

    public GlobalHeaders removeHeader(String name) {
        if (name != null) {
            headers.remove(name.trim());
        }
        return this;
    }

    public List<String> headerList(String name) {
        if (StrUtil.isBlank(name)) {
            return null;
        }

        return headers.get(name.trim());
    }

    public String header(String name) {
        final List<String> values = headerList(name);
        if (CollectionUtil.isEmpty(values)) {
            return null;
        }
        return values.get(0);
    }

    public String header(Header name) {
        if (null == name) {
            return null;
        }
        return header(name.toString());
    }

    public GlobalHeaders header(String name, String value, boolean isOverride) {
        if (null != name && null != value) {
            final List<String> values = headers.get(name.trim());
            if (isOverride || CollectionUtil.isEmpty(values)) {
                final ArrayList<String> valueList = new ArrayList<>();
                valueList.add(value);
                headers.put(name.trim(), valueList);
            } else {
                values.add(value.trim());
            }
        }
        return this;
    }

    public GlobalHeaders header(Header name, String value, boolean isOverride) {
        return header(name.toString(), value, isOverride);
    }

    public GlobalHeaders removeHeader(Header name) {
        return removeHeader(name.toString());
    }

    public GlobalHeaders header(Header name, String value) {
        return header(name.toString(), value, true);
    }

    public GlobalHeaders header(String name, String value) {
        return header(name, value, true);
    }
    public GlobalHeaders header(Map<String, List<String>> headers) {
        if (MapUtil.isEmpty(headers)) {
            return this;
        }

        String name;
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            name = entry.getKey();
            for (String value : entry.getValue()) {
                this.header(name, StrUtil.nullToEmpty(value), false);
            }
        }
        return this;
    }

    public Map<String, List<String>> headers() {
        return Collections.unmodifiableMap(headers);
    }
}
