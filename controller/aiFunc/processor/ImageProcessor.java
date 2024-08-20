package com.example.meitu2.controller.aiFunc.processor;

import com.example.meitu2.utils.ConfigLoader;
import com.example.meitu2.utils.baiduUtils.Base64Util;
import com.example.meitu2.utils.baiduUtils.HttpUtil;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

public interface ImageProcessor {
    String processImage(String imageBase64, Map<String, Object> args);

    default String queryURL(String imageBase64,String url, Map<String, Object> args){
        try {
            byte[] imgData = Base64Util.decode(imageBase64);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, StandardCharsets.UTF_8);

            StringBuilder param = new StringBuilder("image=" + imgParam);
            for (Map.Entry<String,Object> entry: args.entrySet()) {
                param.append("&").
                        append(entry.getKey()).
                        append("=").
                        append(entry.getValue());
            }
            String accessToken = ConfigLoader.getProperty("baidu_accessToken");

            return HttpUtil.post(url, accessToken, param.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
