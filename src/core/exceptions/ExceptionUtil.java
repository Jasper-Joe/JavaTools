package core.exceptions;

import core.util.StrUtil;

public class ExceptionUtil {

    public static String getMessage(Throwable e) {
        if (e == null) {
            return StrUtil.NULL;
        }
        return StrUtil.format("{}: {}", e.getClass().getSimpleName(), e.getMessage());
    }
}
