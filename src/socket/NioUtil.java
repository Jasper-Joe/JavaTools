package socket;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.Selector;

public class NioUtil {
    public static final int DEFAULT_BUFFER_SIZE = 2 << 12;
    public static final int DEFAULT_MIDDLE_BUFFER_SIZE = 2 << 13;
    public static final int DEFAULT_LARGE_BUFFER_SIZE = 2 << 14;
    // end of file
    public static final int EOF = -1;
    public static void registerChannel(Selector selector, SelectableChannel channel, Operation ops) {
        if (channel == null) {
            return;
        }
        try {
            channel.configureBlocking(false);
            channel.register(selector, ops.getValue());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
