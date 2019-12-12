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
public class GitLabMergeRequest extends ParentRequest{


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
        private String targetBranch;
        private String sourceBranch;
        private String title;
        private String description;
        private Source source;

        private Assignee assignee;

        @Data
        public static class Assignee{
            private String name;
        }
        public static class Source{

            private String name;
        }
    }
}
