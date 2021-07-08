package http.server.action;

import http.server.HttpServerRequest;

import java.io.IOException;

@FunctionalInterface
public interface Action {
    void doAction(HttpServerRequest request, HttpServerRequest response) throws IOException;

}
