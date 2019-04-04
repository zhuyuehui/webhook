package com.xesapp.webhook.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xesapp.webhook.exception.FailedHandleHttpResponseException;
import com.xesapp.webhook.exception.FailedHttpCallingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;


/**
 * @author tal_zhuyuehui 2019-04-04
 */
@Slf4j
public class CommonHttpUtils {
    private static final SuccessResponseHandler DEFAULT_SUCCESS_HANDLER = (httpResponse, resultType, enableCamelCase) -> {
        try {
            log.info("SuccessResponseHandler | remote http response: {}", httpResponse.getResponseBody());
            return JsonUtil.deserializeFromJson(httpResponse.getResponseBody(), resultType, enableCamelCase);
        } catch (Exception e) {
            log.warn("SuccessResponseHandler | Failed to get http response entity: entity: {}", httpResponse.getResponseBody());
            throw new FailedHandleHttpResponseException("Failed to get http response entity: code: 200", e);
        }
    };

    private static final FailedResponseHandler DEFAULT_FAILED_HANDLER = (httpResponse, resultType) -> {
        int code = httpResponse.getHttpCode();
        String responseBody = httpResponse.getResponseBody();
        log.warn("FailedResponseHandler | http response is failed: code: {}, entity: {}", code, responseBody);
        throw new FailedHttpCallingException(code, responseBody);
    };


    private static <T> T handleHttpResponse(HttpClientResponse httpResponse, TypeReference<T> resultType, SuccessResponseHandler<T> successResponseHandler, FailedResponseHandler<T> failedResponseHandler, boolean enableCamelCase) {
        if (null == httpResponse) {
            log.warn("handleHttpResponse | HttpResponse is null");
            throw new FailedHandleHttpResponseException("HttpResponse is null");
        }
        int code = httpResponse.getHttpCode();
        if (code == HttpStatus.SC_OK) {
            return successResponseHandler.apply(httpResponse, resultType, enableCamelCase);
        }
        return failedResponseHandler.apply(httpResponse, resultType);
    }

    public static <T> T handleHttpResponse(HttpClientResponse httpResponse, TypeReference<T> resultType) {
        return handleHttpResponse(httpResponse, resultType, false);
    }

    private static <T> T handleHttpResponse(HttpClientResponse httpResponse, TypeReference<T> resultType, boolean enableCamelCase) {
        return handleHttpResponse(httpResponse, resultType, (SuccessResponseHandler<T>) DEFAULT_SUCCESS_HANDLER, (FailedResponseHandler<T>) DEFAULT_FAILED_HANDLER, enableCamelCase);
    }

    @FunctionalInterface
    public interface SuccessResponseHandler<T> {
        T apply(HttpClientResponse httpResponse, TypeReference<T> resultType, boolean enableCamelCase);
    }

    @FunctionalInterface
    public interface FailedResponseHandler<T> {
        T apply(HttpClientResponse httpResponse, TypeReference<T> resultType);
    }

    private CommonHttpUtils() {
        throw new IllegalAccessError("CommonHttpUtils is a utils class");
    }
}
