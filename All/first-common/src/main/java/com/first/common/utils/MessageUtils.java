package com.first.common.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @author ：libofei
 * @description：晚安
 * @date ：2022/5/25 21:55
 */
@Data
public class MessageUtils {



    private  boolean isSystemMessage;//是否系统在线


    private String fromUser;

    private Object message;//系统人数


    public static String getMessage(boolean isSystemMessage,String fromUser,Object message) {
        MessageUtils messageUtils = new MessageUtils();
        messageUtils.setSystemMessage(isSystemMessage);
        messageUtils.setMessage(message);
        messageUtils.setFromUser(fromUser);
        String jsonString = JSONObject.toJSONString(messageUtils);
        return  jsonString;
    }
}
