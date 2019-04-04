package com.xesapp.webhook.vendor.ding.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @JsonProperty("isAtAll")
    private boolean isAtAll;
}
