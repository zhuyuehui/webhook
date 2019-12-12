package com.github.webhook.service.impl;

import com.github.webhook.constant.MessageTypeConstant;
import com.github.webhook.request.GitLabMergeRequest;
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
@Service("mergeEventService")
@Slf4j
public class MergeEventServiceImpl implements EventService {

    @Autowired
    private DingPushService dingPushService;

    @Override
    public void handleEvent(String json) throws IOException {
        GitLabMergeRequest gitLabMergeRequest = covertJson(json);
        String text = "- event : " + gitLabMergeRequest.getObjectKind() + " \n" +
                "- project : " + gitLabMergeRequest.getProject().getName() + "\n" +
                "- source : " + gitLabMergeRequest.getObjectAttributes().getSourceBranch() + "\n" +
                "- target ：" + gitLabMergeRequest.getObjectAttributes().getTargetBranch() + "\n" +
                "- desc ：" + gitLabMergeRequest.getObjectAttributes().getDescription() + "\n";

        MarkDownMessage markDownMessage = new MarkDownMessage(MessageTypeConstant.MARKDOWN_TYPE, new MarkDownMessage.MarkDown(gitLabMergeRequest.getObjectKind(), text));
        //markDownMessage.setAt(new MarkDownMessage.At(Collections.singletonList("15910807274"),false));
        dingPushService.pushMarkDownMessage(markDownMessage);
    }

    private GitLabMergeRequest covertJson(String json) throws IOException {
        return JsonUtil.deserializeFromJson(json, GitLabMergeRequest.class);
    }
}
