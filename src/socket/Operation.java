package socket;

import java.nio.channels.SelectionKey;

public enum Operation {
    READ(SelectionKey.OP_READ),
    WRITE(SelectionKey.OP_WRITE),
    CONNECT(SelectionKey.OP_CONNECT),
    ACCEPT(SelectionKey.OP_ACCEPT);
    private final int value;
    Operation(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
