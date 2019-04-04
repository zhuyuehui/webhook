package com.xesapp.webhook.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tal_zhuyuehui 2019-04-04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpClientResponse {
    private int httpCode;
    private String responseBody;
}
