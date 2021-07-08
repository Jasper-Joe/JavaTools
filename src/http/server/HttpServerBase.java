package http.server;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class HttpServerBase {
    final static Charset DEFAULT_CHAESET = StandardCharsets.UTF_8;
    final HttpExchange httpExchange;

    public HttpServerBase(HttpExchange httpExchange) {
        this.httpExchange = httpExchange;
    }

    public HttpExchange getHttpExchange() {
        return this.httpExchange;
    }

    public HttpContext getHttpContext() {
        return getHttpExchange().getHttpContext();
    }
}
