package core.util;

import core.exceptions.UtilException;
import core.io.IORuntimeException;

import java.io.IOException;
import java.net.*;
import java.nio.charset.Charset;

public class URLUtil {

    public static final String CLASSPATH_URL_PREFIX = "classpath:";
    /**
     * URL 前缀表示文件: "file:"
     */
    public static final String FILE_URL_PREFIX = "file:";
    /**
     * URL 前缀表示jar: "jar:"
     */
    public static final String JAR_URL_PREFIX = "jar:";
    /**
     * URL 前缀表示war: "war:"
     */
    public static final String WAR_URL_PREFIX = "war:";
    /**
     * URL 协议表示文件: "file"
     */
    public static final String URL_PROTOCOL_FILE = "file";
    /**
     * URL 协议表示Jar文件: "jar"
     */
    public static final String URL_PROTOCOL_JAR = "jar";
    /**
     * URL 协议表示zip文件: "zip"
     */
    public static final String URL_PROTOCOL_ZIP = "zip";
    /**
     * URL 协议表示WebSphere文件: "wsjar"
     */
    public static final String URL_PROTOCOL_WSJAR = "wsjar";
    /**
     * URL 协议表示JBoss zip文件: "vfszip"
     */
    public static final String URL_PROTOCOL_VFSZIP = "vfszip";
    /**
     * URL 协议表示JBoss文件: "vfsfile"
     */
    public static final String URL_PROTOCOL_VFSFILE = "vfsfile";
    /**
     * URL 协议表示JBoss VFS资源: "vfs"
     */
    public static final String URL_PROTOCOL_VFS = "vfs";
    /**
     * Jar路径以及内部文件路径的分界符: "!/"
     */
    public static final String JAR_URL_SEPARATOR = "!/";
    /**
     * WAR路径及内部文件路径分界符
     */
    public static final String WAR_URL_SEPARATOR = "*/";

    public static URI toURI(URL url, boolean isEncode) throws UtilException {
        if (url == null) {
            return null;
        }
        return toURI(url.toString(), isEncode);

    }

    public static URL url(String url, URLStreamHandler handler) {
        if (url.startsWith(CLASSPATH_URL_PREFIX)) {
            url = url.substring(CLASSPATH_URL_PREFIX.length());
            return Class
        }
    }

    public static URL url(String url) {
        return url(url, null);
    }

    public static String encodeBlank(CharSequence urlStr) {
        if (urlStr == null) {
            return null;
        }

        int len = urlStr.length();
        final StringBuilder sb = new StringBuilder(len);
        char c;
        for (int i = 0; i < len; i++) {
            c = urlStr.charAt(i);
            if (CharUtil.isBlankChar(c)) {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }


    public static String encode(String url, Charset charset) {
        if (StrUtil.isEmpty(url)) {
            return url;
        }
        if (null == charset) {
            charset = CharsetUtil.defaultCharset();
        }
        return URLEncoder.DEFAULT.encode(url, charset);
    }



    public static URI toURI(URL url) throws UtilException {
        return toURI(url, false);
    }
    public static URI toURI(String location, boolean isEncode) throws UtilException {
        if (isEncode) {
            location = encode(location);
        }
        try {
            return new URI(StrUtil.trim(location));
        } catch (URISyntaxException e) {
            throw new UtilException(e);
        }
    }

    public static long getContentLength(URL url) throws IORuntimeException {
        if (null == url) {
            return -1;
        }

        URLConnection conn = null;
        try {
            conn = url.openConnection();
            return conn.getContentLengthLong();
        } catch (IOException e) {
            throw new IORuntimeException(e);
        } finally {
            if (conn instanceof HttpURLConnection) {
                ((HttpURLConnection) conn).disconnect();
            }
        }
    }


    public static String getDataUriBase64(String mimeType, String data) {
        return getDataUri(mimeType, null, "base64", data);
    }

    public static String getDataUri(String mimeType, String encoding, String data) {
        return getDataUri(mimeType, null, encoding, data);
    }

    public static String getDataUri(String mimeType, Charset charset, String encoding, String data) {
        final StringBuilder builder = StrUtil.builder();
        builder.append("data:");
        if (StrUtil.isNotBlank(mimeType)) {
            builder.append(mimeType);
        }
        if (null != charset) {
            builder.append(";charset=").append(charset.name());
        }
        if (StrUtil.isNotBlank(encoding)) {
            builder.append(';').append(encoding);
        }
        builder.append(',').append(data);

        return builder.toString();
    }

}
