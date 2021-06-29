package http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

public class HttpConnection {
    private final URL url;
    private final Proxy proxy;
    private HttpURLConnection conn;

    public HttpConnection(URL url, Proxy proxy) {
        this.url = url;
        this.proxy = proxy;
        initConn();
    }

    public static HttpConnection create(URL url, Proxy proxy) {
        return new HttpConnection(url, proxy);
    }

    public HttpConnection header(String header, String value, boolean isOverride) {
        if (this.conn != null) {
            if (isOverride) {
                this.conn.setRequestProperty(header, value);
            } else {
                this.conn.addRequestProperty(header, value);
            }
        }
        return this;
    }

    public HttpConnection header(Header header, String value, boolean isOverride) {
        return header(header.toString(), value, isOverride);
    }

    public HttpConnection initConn() {
        try {
            this.conn = openHttp();
        } catch(IOException e) {
            throw new HttpException(e);
        }

        this.conn.setDoInput(true);
        return this;
    }

    public URL getUrl() {
        return url;
    }

    public Proxy getProxy() {
        return proxy;
    }

    public HttpURLConnection getHttpURLConnection() {
        return conn;
    }

    public HttpConnection setCookie(String cookie) {
        if (cookie != null) {
            header(Header.COOKIE, cookie, true);
        }
    }

    public HttpConnection setReadTimeout(int timeout) {
        if (timeout > 0 && this.conn != null) {
            this.conn.setReadTimeout(timeout);
        }
        return this;
    }

    public HttpConnection disableCache() {
        this.conn.setUseCaches(false);
        return this;
    }

    private URLConnection openConnection() throws IOException {
        return (this.proxy == null) ? url.openConnection() : url.openConnection(this.proxy);
    }

    private HttpURLConnection openHttp() throws IOException {
        final URLConnection conn = openConnection();
        if (false == conn instanceof HttpURLConnection) {
            throw new HttpException("Http Exception");
        }

        return (HttpURLConnection) conn;
    }

//    public static HttpConnection create()
}
