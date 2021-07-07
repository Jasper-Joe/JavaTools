package core.net.url;

import core.net.URLDecoder;
import core.util.CharUtil;
import core.util.StrUtil;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

public class UrlPath {
    private boolean withEngTag;
    private List<String> segments;

    private static String fixPath(CharSequence path) {
        if ("/".contentEquals(path)) {
            return StrUtil.EMPTY;
        }
        String segmentStr = StrUtil.trim(path);
        segmentStr = StrUtil.removePrefix(segmentStr, StrUtil.SLASH);
        segmentStr = StrUtil.removeSuffix(segmentStr, StrUtil.SLASH);
        segmentStr = StrUtil.trim(segmentStr);
        return segmentStr;
    }

    private void addInternal(CharSequence segment, boolean before) {
        if (this.segments == null) {
            this.segments = new LinkedList<>();
        }
        final String seg = StrUtil.str(segment);
        if (before) {
            this.segments.add(0, seg);
        } else {
            this.segments.add(seg);
        }
    }

    public UrlPath parse(String path, Charset charset) {
        if (StrUtil.isNotEmpty(path)) {
            if (StrUtil.endWith(path, CharUtil.SLASH)) {
                this.withEngTag = true;
            }
            path = fixPath(path);
            final List<String> split = StrUtil.split(path, '/');
            for (String seg : split) {
                addInternal(URLDecoder.decodeForPath(seg, charset), false);
            }
        }
        return this;

    }

    public static UrlPath of(String pathStr, Charset charset) {
        final UrlPath urlPath = new UrlPath();
        urlPath.parse(pathStr, charset);
        return urlPath;
    }


}
