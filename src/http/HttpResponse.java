package http;

import http.cookie.GlobalCookieManager;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpRequest;
import java.nio.charset.Charset;

public class HttpResponse extends HttpBase<HttpResponse> implements Closeable {
    protected HttpConnection httpConnection;
    protected InputStream in;
    private volatile boolean isAsync;

    protected int status;
    private final boolean ignoreBody;

    public int getStatus() {
        return this.status;
    }

    public boolean isOk() {
        return this.status >= 200 && this.status < 300;
    }

    private HttpResponse init() throws HttpException {
        try {
            this.status = httpConnection.responseCode();
        }catch (IOException e) {
            if (!(e instanceof FileNotFoundException)) {
                throw new HttpException(e);
            }
        }

        try {
            this.headers = httpConnection.headers();

        } catch (IllegalArgumentException e) {

        }

        GlobalCookieManager.store(httpConnection);
        final Charset charset = httpConnection.getCharset();
        this.charsetFromResponse = charset;
        if (charset != null) {
            this.charset = charset;
        }

        this.in = new HttpInputStream(this);
        return this.isAsync ? this : forceSync();

    }

    public HttpRequest disableCache() {
        this.isDisableCache = true;
        return this;
    }

    @Override
    public void close() throws IOException {

    }
}
