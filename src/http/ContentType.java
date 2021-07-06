package http;

import core.util.StrUtil;

import java.nio.charset.Charset;

public enum ContentType {
    FORM_URLENCODED("application/x-www-form-urlencoded"),
    MULTIPART("multipart/form-data"),
    JSON("application/json"),
    TEXT_PLAIN("text/plain"),
    TEXT_XML("text/xml"),
    TEXT_HTML("text/html"),
    XML("application.xml");


    public static boolean isFromUrlEncode(String contentType) {
        return StrUtil.startWithIgnoreCase(contentType, FORM_URLENCODED.toString());
    }

    public static ContentType get(String body) {
        ContentType contentType = null;
        if (StrUtil.isNotBlank(body)) {
            char firstChar = body.charAt(0);
            switch (firstChar) {
                case '{':
                case '[':
                    contentType = JSON;
                    break;
                case '<':
                    contentType = XML;
                    break;
                default:
                    break;
            }
        }

        return contentType;
    }

    public static String build(String contentType, Charset charset) {
        return StrUtil.format("{};charset={}", contentType, charset.name());
    }

    private final String value;

    ContentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String toString(Charset charset) {
        return build(this.value, charset);
    }

    public static boolean isDefault(String contentType) {
        return contentType == null || isFromUrlEncode(contentType);
    }

    @Override
    public String toString() {
        return getValue();
    }


}
