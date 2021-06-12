package socket;

import javax.swing.text.html.HTMLDocument;
import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioClient implements Closeable {
    private Selector selector;
    private SocketChannel channel;
    private ChannelHandler handler;

    public NioClient init(InetSocketAddress address) throws IOException {
        try {
            this.channel = SocketChannel.open();
            channel.configureBlocking(false);
            channel.connect(address);
            this.selector = Selector.open();
            channel.register(this.selector, SelectionKey.OP_READ);
            while (!channel.finishConnect()) {}

        } catch (IOException e) {
            close();
            throw new RuntimeException(e);
        }
        return this;
    }

    public void listen() {
        ThreadUtil.execute(() -> {
            try {
                doListen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
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
        if (key.isReadable()) {
            final SocketChannel socketChannel = (SocketChannel)key.channel();
            try {
                handler.handle(socketChannel);
            } catch(Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public NioClient write(ByteBuffer... data) {
        try {
            this.channel.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public SocketChannel getChannel() {
        return this.channel;
    }

    @Override
    public void close() throws IOException {
        IoUtil.close(this.selector);
        IoUtil.close(this.channel);

    }
}
