package com.github.webhook.service.impl;

import com.github.webhook.constant.MessageTypeConstant;
import com.github.webhook.request.GitLabNoteRequest;
import com.github.webhook.service.DingPushService;
import com.github.webhook.util.JsonUtil;
import com.github.webhook.service.EventService;
import com.github.webhook.vendor.ding.data.MarkDownMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author tal_zhuyuehui 2019-04-04
 */
@Service("noteEventService")
@Slf4j
public class NoteEventServiceImpl implements EventService {

    @Autowired
    private DingPushService dingPushService;

    @Override
    public void handleEvent(String json) throws IOException {
        GitLabNoteRequest gitLabNoteRequest = covertJson(json);
        String text = "- event : " + gitLabNoteRequest.getObjectKind() + " \n" +
                "- project : " + gitLabNoteRequest.getProject().getName() + "\n" +
                "- commentUser : " + gitLabNoteRequest.getUser().getName() + "\n" +
                "- comment ：" + gitLabNoteRequest.getObjectAttributes().getNote() + "\n" +
                "- see ：" + gitLabNoteRequest.getObjectAttributes().getUrl() + "\n";

        dingPushService.pushMarkDownMessage(new MarkDownMessage(MessageTypeConstant.MARKDOWN_TYPE, new MarkDownMessage.MarkDown(gitLabNoteRequest.getObjectKind(), text)));
    }

    private GitLabNoteRequest covertJson(String json) throws IOException {
        return JsonUtil.deserializeFromJson(json, GitLabNoteRequest.class);
    }
}
