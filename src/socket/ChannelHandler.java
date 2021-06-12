package socket;

import java.nio.channels.SocketChannel;

@FunctionalInterface
public interface ChannelHandler {
    void handle(SocketChannel socketChannel) throws Exception;
}
