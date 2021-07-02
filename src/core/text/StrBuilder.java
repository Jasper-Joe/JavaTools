package core.text;

import core.convert.Convert;
import core.util.StrUtil;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

public class StrBuilder implements CharSequence, Appendable, Serializable {
    public static final int DEFAULT_CAPACITY = 16;

    private char[] value;
    private int position;

    public static StrBuilder create() {
        return new StrBuilder();
    }

    public static StrBuilder create(int initialCapacity) {
        return new StrBuilder(initialCapacity);
    }

    public StrBuilder() {
        this(DEFAULT_CAPACITY);
    }

    public StrBuilder(int initialCapacity) {
        value = new char[initialCapacity];
    }

    public StrBuilder insert(int index, CharSequence csq, int start, int end) {
        if (csq == null) {
            csq = "null";
        }
        final int csqLen = csq.length();
        if (start > csqLen) {
            return this;
        }
        if (start < 0) {
            start = 0;
        }
        if (end > csqLen) {
            end = csqLen;
        }
        if (start >= end) {
            return this;
        }
        if (index < 0) {
            index = 0;
        }
        final int length = end - start;
        moveDataAfterIndex(index, length);
        for (int i = start, j = this.position; i < end; i++, j++) {
            value[j] = csq.charAt(i);
        }
        this.position = Math.max(this.position, index) + length;
        return this;
    }

    private void expandCapacity(int minimumCapacity) {
        int newCapacity = (value.length << 1) + 2;
        if (newCapacity - minimumCapacity < 0) {
            newCapacity = minimumCapacity;
        }
        if (newCapacity < 0) {
            throw new OutOfMemoryError("Capacity is too long");
        }
        value = Arrays.copyOf(value, newCapacity);
    }

    private void ensureCapacity(int minimumCapacity) {
        if (minimumCapacity - value.length > 0) {
            expandCapacity(minimumCapacity);
        }
    }

    private void moveDataAfterIndex(int index, int length) {
        ensureCapacity(Math.max(this.position, index) + length);
        if (index < this.position) {
            System.arraycopy(this.value, index, this.value, index + length, this.position - index);
        } else if (index > this.position) {
            Arrays.fill(this.value, this.position, index, StrUtil.C_SPACE);
        }
    }

    public StrBuilder insert(int index, Object obj) {
        if (obj instanceof CharSequence) {
            return insert(index, (CharSequence) obj);
        }
        return insert(index, Convert.toStr(obj));
    }

    public StrBuilder append(Object obj) {
        return insert(this.position, obj);
    }

    @Override
    public StrBuilder append(char c) {
        return insert(this.position, c);
    }
    @Override
    public StrBuilder append(CharSequence csq, int start, int end) {
        return insert(this.position, csq, start, end);
    }



    @Override
    public StrBuilder append(CharSequence csq) {
        return insert(this.position, csq);
    }



    @Override
    public int length() {
        return 0;
    }

    @Override
    public char charAt(int index) {
        return 0;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return null;
    }
}
