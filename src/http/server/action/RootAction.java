package http.server.action;

import core.collection.CollUtil;
import http.server.HttpServerRequest;

import java.io.File;
import java.util.List;

public class RootAction implements Action{
    private final File rootDir;
    private final List<String> indexFileNames;
    public static final String DEFAULT_INDEX_FILE_NAME = "index.html";

    public RootAction(String rootDir) {
        this(new File(rootDir));
    }

    public RootAction(File rootDir) {
        this(rootDir, DEFAULT_INDEX_FILE_NAME);
    }

    public RootAction(String rootDir, String... indexFileNames) {
        this(new File(rootDir), indexFileNames);
    }

    public RootAction(File rootDir, String... indexFileNames) {
        this.rootDir = rootDir;
        this.indexFileNames = CollUtil.toList(indexFileNames);
    }

    public void doAction(HttpServerRequest request, HttpServerResponse response) {
        final String path = request.getPath();
        File file = FileUtil.file(rootDir, path);
        if (file.exists()) {
            if (file.isDirectory()) {
                for (String indexFileName : indexFileNames) {
                    file = FileUtil.file(file, indexFileName);
                    if (file.exists() && file.isFile()) {
                        response.write(file);
                    }
                }
            } else {
                final String name = request.getParam("name");
                response.write(file, name);
            }
        }

        response.send404("404 Not Found!");
    }
}
