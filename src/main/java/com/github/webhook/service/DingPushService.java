package com.github.webhook.service;

import com.github.webhook.vendor.ding.data.MarkDownMessage;
/**
 * @author tal_zhuyuehui 2019-04-04
 */
public interface DingPushService {

    /**
     * push message
     * @param markDownMessage markdown message
     */
    void pushMarkDownMessage(MarkDownMessage markDownMessage);
}
