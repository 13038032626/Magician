package com.example.meitu2.controller.aiFunc.processor.processorImpl;

import com.example.meitu2.controller.aiFunc.processor.ImageProcessor;

import java.util.Map;

public class RecognitionProcessor implements ImageProcessor {
    @Override
    public String processImage(String imageBase64, Map<String, Object> args) {
        String url = "https://aip.baidubce.com/rest/2.0/image-classify/v2/advanced_general";
        return queryURL(imageBase64,url,null);
    }
}
