package socket;

import java.io.Serializable;

public class SocketConfig implements Serializable {
    private long readTimeout;
    private long writeTimeout;

    private int readBufferSize = IoUtil.DEFAULT_BUFFER_SIZE;
    private int writeBufferSize = IoUtil.DEFAULT_BUFFER_SIZE;

    private static final int CPU_COUNT = RuntimeUtil.getProcessorCount();
    private int threadPoolSize = CPU_COUNT;

    public int getThreadPoolSize() {
        return threadPoolSize;
    }

    public void setThreadPoolSize(int threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }

    public long getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(long readTimeout) {
        this.readTimeout = readTimeout;
    }

    public long getWriteTimeout() {
        return writeTimeout;
    }

    public void setWriteTimeout(long writeTimeout) {
        this.writeTimeout = writeTimeout;
    }

    public int getReadBufferSize() {
        return writeBufferSize;
    }

    public void setWriteBufferSize(int writeBufferSize) {
        this.writeBufferSize = writeBufferSize;
    }
}
