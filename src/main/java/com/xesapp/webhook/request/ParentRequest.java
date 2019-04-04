package com.xesapp.webhook.request;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;
/**
 * @author tal_zhuyuehui 2019-04-04
 */
@Data
public abstract class ParentRequest {
    private String objectKind;
    private String projectId;
    private Project project;
    private Repository repository;
    private List<Commit> commits;
    @Data
    public static class Project{
        private Long id;
        private String name;
        private String description;
        private String webUrl;
        private String avatarUrl;
        private String gitSshUrl;
        private String gitHttpUrl;
        private String namespace;
        private String visibilityLevel;
        private String pathWithNamespace;
        private String defaultBranch;

    }

    @Data
    public static class Repository{
        private String name;
        private String url;
        private String description;
        private String homePage;
    }

    @Data
    public static class Commit{
        private String id;
        private String message;
        private ZonedDateTime zonedDateTime;
        private Author author;
        private String[] added;
        private String[] modified;
        private String[] removed;

        @Data
        public static class Author{
            private String name;
            private String email;
        }
    }
}
