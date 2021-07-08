package core.util;

import core.io.FileUtil;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;

public class CharsetUtil {
    public static final String ISO_8859_1 = "ISO-8859-1";
    /**
     * UTF-8
     */
    public static final String UTF_8 = "UTF-8";
    /**
     * GBK
     */
    public static final String GBK = "GBK";

    /**
     * ISO-8859-1
     */
    public static final Charset CHARSET_ISO_8859_1 = StandardCharsets.ISO_8859_1;
    /**
     * UTF-8
     */
    public static final Charset CHARSET_UTF_8 = StandardCharsets.UTF_8;
    /**
     * GBK
     */
    public static final Charset CHARSET_GBK;

    static {
        //避免不支持GBK的系统中运行报错 issue#731
        Charset _CHARSET_GBK = null;
        try {
            _CHARSET_GBK = Charset.forName(GBK);
        } catch (UnsupportedCharsetException e) {
            //ignore
        }
        CHARSET_GBK = _CHARSET_GBK;
    }

    public static File convert(File file, Charset srcCharset, Charset destCharset) {
        final String str = FileUtil.readString(file, srcCharset);
        return FileUtil.writeString(str, file, destCharset);
    }

    public static String convert(String source, Charset srcCharset, Charset destCharset) {
        if (null == srcCharset) {
            srcCharset = StandardCharsets.ISO_8859_1;
        }

        if (null == destCharset) {
            destCharset = StandardCharsets.UTF_8;
        }

        if (StrUtil.isBlank(source) || srcCharset.equals(destCharset)) {
            return source;
        }
        return new String(source.getBytes(srcCharset), destCharset);
    }

    public static Charset parse(String charsetName, Charset defaultCharset) {
        if (StrUtil.isBlank(charsetName)) {
            return defaultCharset;
        }

        Charset result;
        try {
            result = Charset.forName(charsetName);
        } catch (UnsupportedCharsetException e) {
            result = defaultCharset;
        }

        return result;
    }
}
