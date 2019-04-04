package com.xesapp.webhook.service;

import java.io.IOException;
/**
 * @author tal_zhuyuehui 2019-04-04
 */
public interface GitLabNotifyService {

    /**
     * handle event
     * @param json json
     * @param eventName event
     * @throws IOException exception
     */
    void handleEventData(String json,String eventName) throws IOException;
}
