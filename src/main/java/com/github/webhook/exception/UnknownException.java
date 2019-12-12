package com.github.webhook.exception;

/**
 * @author tal_zhuyuehui 2019-04-04
 */
public class UnknownException extends RuntimeException {
    public UnknownException() {
    }

    public UnknownException(String message) {
        super(message);
    }
}
