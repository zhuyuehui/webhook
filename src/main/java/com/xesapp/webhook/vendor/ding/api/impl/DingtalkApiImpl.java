package com.xesapp.webhook.vendor.ding.api.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xesapp.webhook.exception.FailedHandleHttpResponseException;
import com.xesapp.webhook.exception.FailedHttpCallingException;
import com.xesapp.webhook.exception.UnknownException;
import com.xesapp.webhook.util.CommonHttpUtils;
import com.xesapp.webhook.util.HttpClientResponse;
import com.xesapp.webhook.util.HttpClientWrapper;
import com.xesapp.webhook.util.JsonUtil;
import com.xesapp.webhook.vendor.ding.DingResponse;
import com.xesapp.webhook.vendor.ding.api.DingTalkApi;
import com.xesapp.webhook.vendor.ding.data.MarkDownMessage;
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
