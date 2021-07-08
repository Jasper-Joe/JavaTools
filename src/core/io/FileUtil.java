package core.io;

import core.io.file.FileNameUtil;
import core.util.ArrayUtil;
import core.util.StrUtil;
import socket.IoUtil;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

public class FileUtil {

    public static boolean isWindows() {
        return FileNameUtil.WINDOWS_SEPARATOR == File.separatorChar;
    }

    public static File file(File parent, String path) {
        if (StrUtil.isBlank(path)) {
            throw new NullPointerException("File path is blank");
        }
        return checkSlip(parent, buildFile(parent, path));
    }
    public static File file(File directory, String... names) {
        if (ArrayUtil.isEmpty(names)) {
            return directory;
        }

        File file = directory;
        for (String name : names) {
            if (name != null) {
                file = file(file, name);
            }
        }
        return file;
    }

    public static String readableFileSize(File file) {
        return readableFileSize(file.length());
    }

    public static String readableFileSize(long size) {
        return DataSizeUtil.format(size);
    }

    public static String readString(File file, Charset charset) throws IORuntimeException {
        return FileReader.create(file, charset).readString();
    }

    public static String readString(String path, String charsetName) throws IORuntimeException {
        return readString(file(path), charsetName);
    }

    public static String readString(String path, Charset charset) throws IORuntimeException {
        return readString(file(path), charset);
    }

    public static String readString(URL url, String charset) throws IORuntimeException {
        if (url == null) {
            throw new NullPointerException("Empty url provided!");
        }

        InputStream in = null;
        try {
            in = url.openStream();
            return IoUtil.read(in, charset);
        } catch (IOException e) {
            throw new IORuntimeException(e);
        } finally {
            IoUtil.close(in);
        }
    }
}
