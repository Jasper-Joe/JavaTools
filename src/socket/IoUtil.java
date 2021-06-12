package socket;

import java.io.Closeable;

public class IoUtil extends NioUtil{
    public static void close(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (Exception e) {

            }
        }
    }
}
