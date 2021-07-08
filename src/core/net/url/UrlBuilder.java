package core.net.url;

import core.util.CharsetUtil;
import core.util.URLUtil;

import java.io.Serializable;
import java.net.URL;
import java.nio.charset.Charset;

public class UrlBuilder implements Serializable {
    private static final String DEFAULT_SCHEME = "http";
    private String scheme;
    private String host;
    private int port = -1;
    private UrlPath path;
    private UrlQuery query;
    private String fragment;
    private Charset charset;

    public static UrlBuilder of(URL url, Charset charset) {
        return of(url.getProtocol(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef(), charset);
    }

    public static UrlBuilder of(String scheme, String host, int port, String path, String query, String fragment, Charset charset) {
        return of(scheme, host, port, UrlPath.of(path, charset), UrlQuery.of(query, charset, false), fragment, charset);
    }

    public static UrlBuilder of(String url, Charset charset) {
        return of(URLUtil.url(url.trim()), charset);
    }

    public static UrlBuilder ofHttp(String httpUrl) {
        return ofHttp(httpUrl, CharsetUtil.CHARSET_UTF_8);
    }

    public static UrlBuilder ofHttp(String httpUrl, Charset charset) {
        final int sepIndex = httpUrl.indexOf("://");
        if (sepIndex < 0) {
            httpUrl = "http://" + httpUrl.trim();
        }
        return of(httpUrl, charset);
    }
}
