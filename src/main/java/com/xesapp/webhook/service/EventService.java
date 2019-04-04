package com.xesapp.webhook.service;

import java.io.IOException;

/**
 * @author tal_zhuyuehui 2019-04-04
 */
public interface EventService {

    /**
     * handle event
     * @param json data
     * @throws IOException exception
     */
    void handleEvent(String json) throws IOException;
}
