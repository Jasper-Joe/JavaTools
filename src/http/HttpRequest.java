package http;

import core.io.IORuntimeException;
import core.net.url.UrlBuilder;
import http.cookie.GlobalCookieManager;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.CookieManager;
import java.net.Proxy;
import java.net.URLStreamHandler;
import java.util.Map;

public class HttpRequest extends HttpBase{
    //private UrlBuilder url;
    private Method method = Method.GET;
    private URLStreamHandler urlHandler;
    private int connectionTimeout = HttpGlobalConfig.timeout;
    private boolean isMultiPart;
    private Map<String, Object> form;
    private HttpConnection httpConnection;
    private String cookie;
    private boolean isRest;
    private boolean isDisableCache;
    private int redirectCount;
    private int maxRedirectCount;
    private int blockSize;
    private Proxy proxy;
    private HostnameVerifier hostnameVerifier;
    private SSLSocketFactory ssf;
    private UrlBuilder url;

    public HttpRequest(String url) {
        this(UrlBuilder.ofHttp(url));
    }

    public static HttpRequest put(String url) {
        return new HttpRequest(url).method(Method.PUT);
    }

    public HttpRequest(UrlBuilder url) {
        this.url = url;
        this.header(GlobalHeaders.INSTANCE.headers);
    }

    public HttpRequest method(Method method) {
        this.method = method;
        return this;
    }

    public HttpRequest contentType(String contentType) {
        header(Header.CONTENT_TYPE, contentType);
        return this;
    }

    private void send() throws IORuntimeException {
        try {
            if (Method.POST.equals(this.method) //
                    || Method.PUT.equals(this.method) //
                    || Method.DELETE.equals(this.method) //
                    || this.isRest) {
                if (isMultipart()) {
                    sendMultipart(); // 文件上传表单
                } else {
                    sendFormUrlEncoded();// 普通表单
                }
            } else {
                this.httpConnection.connect();
            }
        } catch (IOException e) {
            // 异常时关闭连接
            this.httpConnection.disconnectQuietly();
            throw new IORuntimeException(e);
        }
    }



    public static void setGlobalTimeout(int customTimeout) {
        HttpGlobalConfig.setTimeout(customTimeout);
    }

    public static CookieManager getCookieManager() {
        return GlobalCookieManager.getCookieManager();
    }

    public static void setCookieManager(CookieManager customCookieManager) {
        GlobalCookieManager.setCookieManager(customCookieManager);
    }

    public static void closeCookie() {
        GlobalCookieManager.setCookieManager(null);
    }


}
