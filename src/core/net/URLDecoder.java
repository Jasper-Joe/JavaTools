package core.net;

import core.util.CharUtil;
import core.util.CharsetUtil;
import core.util.StrUtil;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;

public class URLDecoder implements Serializable {
    private static final byte ESCAPE_CHAR = '%';

    public static String decode(String str, Charset charset, boolean isPlusToSpace) {
        return StrUtil.str(decode(StrUtil.bytes(str, charset), isPlusToSpace), charset);
    }

    public static byte[] decode(byte[] bytes) {
        return decode(bytes, true);
    }
    public static byte[] decode(byte[] bytes, boolean isPlusToSpace) {
        if (bytes == null) {
            return null;
        }
        final ByteArrayOutputStream buffer = new ByteArrayOutputStream(bytes.length);
        int b;
        for (int i = 0; i < bytes.length; i++) {
            b = bytes[i];
            if (b == '+') {
                buffer.write(isPlusToSpace ? CharUtil.SPACE : b);
            } else if (b == ESCAPE_CHAR){
                if (i + 1 < bytes.length) {
                    final int u = CharUtil.digit16(bytes[i + 1]);
                    if (u >= 0 && i + 2 < bytes.length) {
                        final int l = CharUtil.digit16(bytes[i + 2]);
                        if (l >= 0) {
                            buffer.write((char) ((u << 4) + l));
                            i += 2;
                            continue;
                        }
                    }
                }
                buffer.write(b);
            } else {
                buffer.write(b);
            }
        }
        return buffer.toByteArray();
    }
}
