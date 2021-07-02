package http.cookie;

import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.List;

public class ThreadLocalCookieStore implements CookieStore {
    private final static ThreadLocal<CookieStore> STORES = new ThreadLocal<CookieStore>() {
        @Override
        protected synchronized CookieStore initialValue() {
            return (new CookieManager()).getCookieStore();
        }
    };

    public CookieStore getCookieStore() {
        return STORES.get();
    }

    public ThreadLocalCookieStore removeCurrent() {
        STORES.remove();
        return this;
    }


    @Override
    public void add(URI uri, HttpCookie cookie) {
        getCookieStore().add(uri, cookie);
    }

    @Override
    public List<HttpCookie> get(URI uri) {
        return getCookieStore().get(uri);
    }

    @Override
    public List<HttpCookie> getCookies() {
        return getCookieStore().getCookies();
    }

    @Override
    public List<URI> getURIs() {
        return getCookieStore().getURIs();
    }

    @Override
    public boolean remove(URI uri, HttpCookie cookie) {
        return getCookieStore().remove(uri,cookie);
    }

    @Override
    public boolean removeAll() {
        return getCookieStore().removeAll();
    }
}
