package socket;

import java.io.Serializable;

public class SocketConfig implements Serializable {
    private long readTimeout;
    private long writeTimeout;

    private int readBufferSize = IoUtil.DEFAULT_BUFFER_SIZE;
}
