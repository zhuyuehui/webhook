package com.github.webhook.vendor.ding.api;

import com.github.webhook.vendor.ding.data.MarkDownMessage;
import com.github.webhook.vendor.ding.DingResponse;

/**
 * @author tal_zhuyuehui 2019-04-04
 */
public interface DingTalkApi {


    /**
     * pushMarkDownMessage
     * @param markDownMessage markDownMessage
     * @return DingResponse
     */
    DingResponse<Void> pushMarkDownMessage(MarkDownMessage markDownMessage);
}
