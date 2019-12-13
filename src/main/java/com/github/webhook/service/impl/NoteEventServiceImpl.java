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

import static org.aspectj.bridge.Version.text;

/**
 * @author tal_zhuyuehui 2019-04-04
 */
@Service("noteEventService")
@Slf4j
public class NoteEventServiceImpl implements EventService {

    @Autowired
    private DingPushService dingPushService;

    private static final String NOTEABLE_TYPE_COMMIT = "Commit";
    private static final String NOTEABLE_TYPE_MERGE_REQUEST = "MergeRequest";
    @Override
    public void handleEvent(String json) throws IOException {
        GitLabNoteRequest gitLabNoteRequest = covertJson(json);
        GitLabNoteRequest.ObjectAttributes objectAttributes = gitLabNoteRequest.getObjectAttributes();
        String noteableType = objectAttributes.getNoteableType();
        String text = "";
        if(NOTEABLE_TYPE_COMMIT.equals(noteableType)){
            text = "- event : " + gitLabNoteRequest.getObjectKind() + " on commit \n" +
                    "- project : " + gitLabNoteRequest.getProject().getName() + "\n" +
                    "- commentUser : " + gitLabNoteRequest.getUser().getName() + "\n" +
                    "- comment ：" + objectAttributes.getNote() + "\n" +
                    "- see ：" + objectAttributes.getUrl() + "\n";
        }else if(NOTEABLE_TYPE_MERGE_REQUEST.equals(noteableType)){
            text = "- event : " + gitLabNoteRequest.getObjectKind() + " on Merge request \n" +
                    "- project : " + gitLabNoteRequest.getProject().getName() + "\n" +
                    "- commentUser : " + gitLabNoteRequest.getUser().getName() + "\n" +
                    "- comment ：" + objectAttributes.getNote() + "\n" +
                    "- from " + gitLabNoteRequest.getMergeRequest().getTargetBranch() + " to " + gitLabNoteRequest.getMergeRequest().getSourceBranch() + "\n" +
                    "- see ：" + objectAttributes.getUrl() + "\n";
        }


        dingPushService.pushMarkDownMessage(new MarkDownMessage(MessageTypeConstant.MARKDOWN_TYPE, new MarkDownMessage.MarkDown(gitLabNoteRequest.getObjectKind(), text)));
    }

    private GitLabNoteRequest covertJson(String json) throws IOException {
        return JsonUtil.deserializeFromJson(json, GitLabNoteRequest.class);
    }
}
