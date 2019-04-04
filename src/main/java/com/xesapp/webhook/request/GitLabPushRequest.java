package com.xesapp.webhook.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;
/**
 * @author tal_zhuyuehui 2019-04-04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GitLabPushRequest extends ParentRequest{


    private String before;
    private String after;
    private String ref;
    private String checkoutSha;
    private String userId;
    private String userName;
    private String userUsername;
    private String userAvatar;
    private List<Commit> commits;

    private int totalCommitsCount;


}
