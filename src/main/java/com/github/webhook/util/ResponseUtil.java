package com.github.webhook.util;

import com.github.webhook.vo.ResponseVo;

public class ResponseUtil {

    private static final Integer SUCCESS_CODE = 0;
    private static final String SUCCESS_MSG = "success";

    public static ResponseVo ok() {
        return new ResponseVo(SUCCESS_CODE, SUCCESS_MSG);
    }
}
