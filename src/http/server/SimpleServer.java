package http.server;

import com.sun.net.httpserver.*;
import core.io.IORuntimeException;
import core.util.StrUtil;
import core.thread.GlobalThreadPool;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class SimpleServer {
    private final HttpServer server;
    private final List<Filter> filters;

    public SimpleServer(int port) {
        this(new InetSocketAddress(port));
    }

    public SimpleServer(String hostname, int port) {
        this(new InetSocketAddress(hostname, port));
    }

    public SimpleServer(InetSocketAddress address) {
        this(address, null);

    }

    public SimpleServer(InetSocketAddress address, HttpsConfigurator configurator) {
        try {
            if (configurator != null) {
                final HttpsServer server = HttpsServer.create(address, 0);
                server.setHttpsConfigurator(configurator);
                this.server = server;
            } else {
                this.server = HttpServer.create(address, 0);
            }
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
        setExecutor(GlobalThreadPool.getExecutor());
        filters = new ArrayList<>();

    }

    public HttpContext createContext(String path, HttpHandler handler) {
        path = StrUtil.addPrefixIfNot(path, StrUtil.SLASH);
        final HttpContext context = this.server.createContext(path, handler);
        context.getFilters().addAll(this.filters);
        return context;
    }

    public SimpleServer addFilter(Filter filter) {
        this.filters.add(filter);
        return this;
    }

    public SimpleServer setExecutor(Executor executor) {
        this.server.setExecutor(executor);
        return this;
    }

    public HttpServer getRawServer() {
        return this.server;
    }

    public InetSocketAddress getAddress() {
        return this.server.getAddress();
    }

    public void start() {
        final InetSocketAddress address = getAddress();
        this.server.start();
    }
}
