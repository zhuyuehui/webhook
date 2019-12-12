package com.github.webhook.exception;

/**
 * @author tal_zhuyuehui 2019-04-04
 */
public class FailedHandleHttpResponseException extends RuntimeException {
    public FailedHandleHttpResponseException(String msg) {
        super(msg);
    }

    public FailedHandleHttpResponseException(String msg, Exception e) {
        super(msg, e);
    }
}
