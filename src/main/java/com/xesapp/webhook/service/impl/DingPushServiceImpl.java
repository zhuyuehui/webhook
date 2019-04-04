package com.xesapp.webhook.service.impl;

import com.xesapp.webhook.exception.UnknownException;
import com.xesapp.webhook.service.DingPushService;
import com.xesapp.webhook.vendor.ding.DingResponse;
import com.xesapp.webhook.vendor.ding.api.DingTalkApi;
import com.xesapp.webhook.vendor.ding.data.MarkDownMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author tal_zhuyuehui 2019-04-04
 */
@Service
@Slf4j
public class DingPushServiceImpl implements DingPushService {


    private static final String SUCCESSS_CODE = "0";
    @Autowired
    private DingTalkApi dingTalkApi;

    @Override
    public void pushMarkDownMessage(MarkDownMessage markDownMessage) {

        DingResponse<Void> response = dingTalkApi.pushCommonMessage(markDownMessage);
        if (!SUCCESSS_CODE.equals(response.getErrcode())) {
            throw new UnknownException("error");
        }

    }
}
