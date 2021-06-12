package socket;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class NioServer implements Closeable {
    //private static final AcceptHan
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private ChannelHandler handler;

    public NioServer(int port) {
        init(new InetSocketAddress(port));
    }

    public NioServer setChannelHandler(ChannelHandler handler) {
        this.handler = handler;
        return this;
    }

    public Selector getSelector() {
        return this.selector;
    }

    public NioServer init(InetSocketAddress address) {
        try {
            this.serverSocketChannel = ServerSocketChannel.open();
            this.serverSocketChannel.configureBlocking(false);
            this.serverSocketChannel.bind(address);
            this.selector = Selector.open();
            this.serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    @Override
    public void close() throws IOException {

    }
}
