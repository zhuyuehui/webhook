package com.github.webhook.controller;

import com.github.webhook.service.GitLabNotifyService;
import com.github.webhook.vo.ResponseVo;
import com.github.webhook.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author tal_zhuyuehui 2019-04-04
 */
@RestController
@RequestMapping("/v1")
@Slf4j
public class GitLabController {


    @Autowired
    private GitLabNotifyService gitLabNotifyService;

    @PostMapping(value = "push-request", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseVo pushHook(@RequestBody String json, @RequestHeader(name = "X-Gitlab-Event") String event) throws IOException{

        gitLabNotifyService.handleEventData(json,event);
        return ResponseUtil.ok();
    }
}
