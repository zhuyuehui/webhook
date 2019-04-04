package com.xesapp.webhook.vendor.ding.api;

import com.xesapp.webhook.vendor.ding.DingResponse;
import com.xesapp.webhook.vendor.ding.data.MarkDownMessage;
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
