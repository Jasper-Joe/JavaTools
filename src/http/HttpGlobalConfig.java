package http;

import http.cookie.GlobalCookieManager;

import java.io.Serializable;
import java.net.CookieManager;

public class HttpGlobalConfig implements Serializable {
    protected static int timeout = -1;

    public static int getTimeout() {
        return timeout;
    }

    public static void setTimeout(int customTimeout) {
        timeout = customTimeout;
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
