package com.xesapp.webhook.exception;

import lombok.Data;

/**
 * @author tal_zhuyuehui 2019-04-04
 */
@Data
public class FailedHttpCallingException extends RuntimeException {
    private final int code;
    private final String msg;
    public FailedHttpCallingException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public FailedHttpCallingException(int code, String msg, Exception e) {
        super(msg, e);
        this.code = code;
        this.msg = msg;
    }
}
