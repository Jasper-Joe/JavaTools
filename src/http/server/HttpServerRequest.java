package http.server;


import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import http.Header;
import http.Method;


import java.net.URI;
import java.nio.charset.Charset;

public class HttpServerRequest extends HttpServerBase{
    private Charset charsetCache;
    public HttpServerRequest (HttpExchange httpExchange) {
        super(httpExchange);
    }

    public String getMethod() {
        return this.httpExchange.getRequestMethod();
    }

    public boolean isGetMethod() {
        return Method.GET.name().equalsIgnoreCase(getMethod());
    }

    public boolean isPostMethod() {
        return Method.POST.name().equalsIgnoreCase(getMethod());
    }

    public URI getURI() {
        return this.httpExchange.getRequestURI();
    }

    public String getPath() {
        return getURI().getPath();
    }

    public String getQuery() {
        return getURI().getQuery();
    }

    public String getHeader(String headerKey) {
        return getHeaders().getFirst(headerKey);
    }

    public String getContentType() {
        return getHeader(String.valueOf(Header.CONTENT_TYPE));
    }

    public Headers getHeaders() {
        return this.httpExchange.getRequestHeaders();
    }


}
