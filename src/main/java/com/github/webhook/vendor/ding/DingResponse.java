package com.github.webhook.vendor.ding;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DingResponse<T> {

    @JsonProperty("errmsg")
    private String errMsg;
    @JsonProperty("errcode")
    private String errcode;
    private T data;
}
