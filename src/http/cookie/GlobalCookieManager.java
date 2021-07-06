package http.cookie;

import core.io.IORuntimeException;
import core.util.URLUtil;
import http.HttpConnection;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalCookieManager {
    private static CookieManager cookieManager;

    static {
        cookieManager = new CookieManager(new ThreadLocalCookieStore(), CookiePolicy.ACCEPT_ALL);
    }

    public static void setCookieManager(CookieManager customCookieManager) {
        cookieManager = customCookieManager;
    }

    public static CookieManager getCookieManager() {
        return cookieManager;
    }

    public static List<HttpCookie> getCookies(HttpConnection conn) {
        return cookieManager.getCookieStore().get(getURI(conn));
    }

    public static void add(HttpConnection conn) {
        if (cookieManager == null) {
            return;
        }
        Map<String, List<String>> cookieHeader;
        try {
            cookieHeader = cookieManager.get(getURI(conn), new HashMap<>(0));
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
        conn.header(cookieHeader, false);

    }

    public static void store(HttpConnection conn) {
        if (cookieManager == null) {
            return;
        }
        try {
            cookieManager.put(getURI(conn), conn.headers());
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }
    

    private static URI getURI(HttpConnection conn) {
        return URLUtil.toURI(conn.getUrl());
    }
}
