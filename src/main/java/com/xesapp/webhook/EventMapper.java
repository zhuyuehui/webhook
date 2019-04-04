package com.xesapp.webhook;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tal_zhuyuehui 2019-04-04
 */
public class EventMapper {

    public static Map<String, String> EVENT_HANDLE_MAP = new HashMap();


    static {
        EVENT_HANDLE_MAP.put("Push Hook", "pushEventService");
        EVENT_HANDLE_MAP.put("Tag Push Hook", "tagPushEventService");

    }


    public static String getHandleBeanName(String eventName) {
        return EVENT_HANDLE_MAP.get(eventName);
    }

}
