package com.github.webhook.vendor.ding.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MarkDownMessage {

    @JsonProperty("msgtype")
    private String msgType;
    private MarkDown markdown;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class MarkDown{
        private String title;
        private String text;
    }

    private At at;

    @Data
    @AllArgsConstructor
    public static class At{
        private List<String> atMobiles;
        @JsonProperty("isAtAll")
        private boolean isAtAll;
    }

    public MarkDownMessage(String msgType,MarkDown markDown){
        this.msgType = msgType;
        this.markdown = markDown;
    }
}
