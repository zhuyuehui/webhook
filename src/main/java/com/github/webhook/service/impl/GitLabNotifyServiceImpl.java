package com.github.webhook.service.impl;

import com.github.webhook.EventMapper;
import com.github.webhook.configuration.ApplicationContextProvider;
import com.github.webhook.service.EventService;
import com.github.webhook.service.GitLabNotifyService;
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
