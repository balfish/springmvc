package com.balfish.common;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class ScmException extends RuntimeException {

    static final long serialVersionUID = 1L;

    public ScmException() {
    }

    public ScmException(String message) {
        super(message);
    }

    public ScmException(String msgTemplate, Object... args) {
        super(String.format(msgTemplate, args));
    }

    public ScmException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScmException(Throwable cause, String msgTemplate, Object... args) {
        super(String.format(msgTemplate, args), cause);
    }

    public ScmException(Throwable cause) {
        super(cause);
    }

}