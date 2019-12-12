package com.github.webhook.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tal_zhuyuehui 2019-04-04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GitLabNoteRequest extends ParentRequest{


   private User user;
   private ObjectAttributes objectAttributes;


   @Data
   public static  class User{
       private String name;
       private String userName;
       private String avatarUrl;
   }

   @Data
    public static  class ObjectAttributes{
        private Integer id;
        private String note;
        private String noteableType;
        private Integer authorId;
        private Integer projectId;
        private String lineCode;
        private String commitId;
        private String url;
    }
}
