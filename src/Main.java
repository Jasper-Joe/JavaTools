import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;
import core.util.CharsetUtil;
import http.Header;
import http.Method;
import http.server.HttpServerRequest;
import socket.RuntimeUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;

public class Main {
    public static void main(String[] args) {
        p(CharsetUtil.CHARSET_UTF_8);






    }

    public static void p(Object res) {
        System.out.println(res);
    }

}
