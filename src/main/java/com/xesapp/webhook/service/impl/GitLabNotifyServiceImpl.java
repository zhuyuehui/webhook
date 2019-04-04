package com.xesapp.webhook.service.impl;

import com.xesapp.webhook.EventMapper;
import com.xesapp.webhook.configuration.ApplicationContextProvider;
import com.xesapp.webhook.request.ParentRequest;
import com.xesapp.webhook.service.EventService;
import com.xesapp.webhook.service.GitLabNotifyService;
import com.xesapp.webhook.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
/**
 * @author tal_zhuyuehui 2019-04-04
 */
@Service
public class GitLabNotifyServiceImpl implements GitLabNotifyService {

    @Autowired
    private ApplicationContextProvider applicationContextProvider;

    @Override
    public void handleEventData(String json, String eventName) throws IOException {
        String handleBeanName = EventMapper.getHandleBeanName(eventName);
        EventService eventService = (EventService) applicationContextProvider.getBean(handleBeanName);
        eventService.handleEvent(json);


    }

}
