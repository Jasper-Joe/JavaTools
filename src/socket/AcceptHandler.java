package socket;

import java.io.IOException;
import java.nio.channels.CompletionHandler;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class AcceptHandler implements CompletionHandler<ServerSocketChannel, NioServer> {
    SocketChannel socketChannel;

    @Override
    public void completed(ServerSocketChannel serverSocketChannel, NioServer nioServer) {
        try {
            socketChannel = serverSocketChannel.accept();

        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        NioUtil.registerChannel(nioServer.getSelector(), socketChannel, Operation.READ);
    }

    @Override
    public void failed(Throwable exc, NioServer attachment) {
        System.out.println("Failed");

    }
}
