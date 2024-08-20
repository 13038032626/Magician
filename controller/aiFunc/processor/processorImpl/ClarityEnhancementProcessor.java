package com.example.meitu2.controller.aiFunc.processor.processorImpl;

import com.example.meitu2.controller.aiFunc.processor.ImageProcessor;

import java.util.Map;

public class ClarityEnhancementProcessor implements ImageProcessor {
    @Override
    public String processImage(String imageBase64, Map<String,Object> args) {
        String url = "https://aip.baidubce.com/rest/2.0/image-process/v1/image_definition_enhance";
        return queryURL(imageBase64,url,null);
    }
}
