package socket;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioServer implements Closeable {
    //private static final AcceptHan
    private static final AcceptHandler ACCEPT_HANDLER = new AcceptHandler();
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

    private void doListen() throws IOException {
        while (this.selector.isOpen() && 0 != this.selector.select()) {
            final Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
            while (keyIter.hasNext()) {
                handle(keyIter.next());
                keyIter.remove();
            }

        }
    }

    private void handle(SelectionKey key) {
        // connect client side to the server side
        if (key.isAcceptable()) {
            ACCEPT_HANDLER.completed((ServerSocketChannel) key.channel(), this);
        }
        if (key.isReadable()) {
            final SocketChannel socketChannel = (SocketChannel) key.channel();
            try {
                handler.handle(socketChannel);
            } catch (Exception e) {
                IoUtil.close(socketChannel);
            }
        }

    }

    @Override
    public void close() throws IOException {
        IoUtil.close(this.selector);
        IoUtil.close(this.serverSocketChannel);

    }
}
