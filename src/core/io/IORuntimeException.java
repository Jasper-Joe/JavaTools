package core.io;

import core.exceptions.ExceptionUtil;
import core.util.StrUtil;

public class IORuntimeException extends RuntimeException{
    public IORuntimeException(Throwable e) {
        super(ExceptionUtil.getMessage(e), e);
    }

    public IORuntimeException(String message) {
        super(message);
    }

    public IORuntimeException(String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params));
    }

    public IORuntimeException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public IORuntimeException (Throwable throwable, String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params), throwable);
    }
}
