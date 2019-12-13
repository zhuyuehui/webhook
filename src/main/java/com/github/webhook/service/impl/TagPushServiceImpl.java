package com.github.webhook.service.impl;

import com.github.webhook.constant.MessageTypeConstant;
import com.github.webhook.request.GitLabPushRequest;
import com.github.webhook.service.DingPushService;
import com.github.webhook.service.EventService;
import com.github.webhook.util.JsonUtil;
import com.github.webhook.vendor.ding.data.MarkDownMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author tal_zhuyuehui 2019-04-04
 */
@Service("tagPushEventService")
@Slf4j
public class TagPushServiceImpl implements EventService {
    @Autowired
    private DingPushService dingPushService;

    @Override
    public void handleEvent(String json) throws IOException {
        GitLabPushRequest gitLabPushRequest = covertJson(json);


        String text = "- event : tag push \n" +
                "- project : " + gitLabPushRequest.getProject().getName() + "\n" +
                "- author : " + gitLabPushRequest.getUserName() + "\n" +
                "- tag ：" + gitLabPushRequest.getRef() + "\n" +
                "- branch ：" + gitLabPushRequest.getProject().getDefaultBranch() + "\n";
        MarkDownMessage tag_push = new MarkDownMessage(MessageTypeConstant.MARKDOWN_TYPE, new MarkDownMessage.MarkDown("tag push", text));
        tag_push.setAt(new MarkDownMessage.At(null,true));
        dingPushService.pushMarkDownMessage(tag_push);
    }

    private GitLabPushRequest covertJson(String json) throws IOException {
        return JsonUtil.deserializeFromJson(json, GitLabPushRequest.class);
    }
}
