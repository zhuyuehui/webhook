package com.github.webhook.vendor.ding.api.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.webhook.exception.FailedHandleHttpResponseException;
import com.github.webhook.exception.FailedHttpCallingException;
import com.github.webhook.exception.UnknownException;
import com.github.webhook.util.CommonHttpUtils;
import com.github.webhook.util.HttpClientResponse;
import com.github.webhook.util.HttpClientWrapper;
import com.github.webhook.util.JsonUtil;
import com.github.webhook.vendor.ding.DingResponse;
import com.github.webhook.vendor.ding.api.DingTalkApi;
import com.github.webhook.vendor.ding.data.MarkDownMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
/**
 * @author tal_zhuyuehui 2019-04-04
 */
@Service
@Slf4j
public class DingtalkApiImpl implements DingTalkApi {

    @Autowired
    private HttpClientWrapper httpClientWrapper;

    @Value("${vendor.ding.dingTalkUrl}")
    private String dingTalkUrl;

    @Override
    public DingResponse<Void> pushMarkDownMessage(MarkDownMessage markDownMessage) {
        HttpClientResponse httpClientResponse;
        try {
            httpClientResponse = httpClientWrapper.postReturnHttpResponse(Collections.singletonMap("Content-Type","application/json"),dingTalkUrl, JsonUtil.serializeToJson(markDownMessage,true));
            return CommonHttpUtils.handleHttpResponse(httpClientResponse, new TypeReference<DingResponse<Void>>() {
            });
        } catch (IOException | FailedHandleHttpResponseException | FailedHttpCallingException e) {

            throw new UnknownException("Failed to execute http request for save article");
        } catch (Exception e) {
            throw new UnknownException("Failed to execute http request");
        }
    }
}
