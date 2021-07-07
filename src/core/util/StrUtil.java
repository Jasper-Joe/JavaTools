package core.util;


import core.text.CharSequenceUtil;
import core.text.StrPool;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Map;

public class StrUtil extends CharSequenceUtil implements StrPool {

    public static String utf8Str(Object obj) {
        return str(obj, CharsetUtil.CHARSET_UTF_8);
    }

    public static String str(byte[] data, Charset charset) {
        if (data == null) {
            return null;
        }
        if (charset == null) {
            return new String(data);
        }
        return new String(data, charset);
    }

    public static String str(ByteBuffer data, Charset charset) {
        if (charset == null) {
            charset = Charset.defaultCharset();
        }
        return charset.decode(data).toString();
    }

    public static String str(ByteBuffer data, String charset) {
        if (data == null) {
            return null;
        }
        return str(data, Charset.forName(charset));
    }

    public static String str(byte[] bytes, String charset) {
        return str(bytes, isBlank(charset) ? Charset.defaultCharset() : Charset.forName(charset));
    }

    public static String str(Object obj, Charset charset) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return (String) obj;
        } else if (obj instanceof byte[]) {
            return str((byte[]) obj, charset);
        } else if (obj instanceof Byte[]) {
            return str((Byte[]) obj, charset);
        } else if (obj instanceof ByteBuffer) {
            return str((ByteBuffer) obj, charset);
        } else if (ArrayUtil.isArray(obj)) {
            return ArrayUtil.toString(obj);
        }
        return obj.toString();
    }
    public static void trim(String[] strs) {
        if (strs == null) {
            return;
        }
        String str;
        for (int i = 0; i < strs.length; i++) {
            str = strs[i];
            if (str != null) {
                strs[i] = CharSequenceUtil.trim(str);
            }
        }
    }


    public static String format(CharSequence template, Map<?, ?> map) {
        return format(template, map, true);
    }

    public static String format(CharSequence template, Map<?, ?> map, boolean ignoreNull) {
        if (template == null) {
            return null;
        }

        if (map == null || map.isEmpty()) {
            return template.toString();
        }

        String template2 = template.toString();
        String value;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            value = utf8Str(entry.getValue());
            if (value == null && ignoreNull) {
                continue;
            }
            template2 = replace(template2, "{" + entry.getKey() + "}", value);
        }
        return template2;
    }
}
