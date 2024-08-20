package com.example.meitu2.controller.aiFunc.processor.processorImpl;

import com.example.meitu2.controller.aiFunc.processor.ImageProcessor;

import java.util.Map;

public class InpaintingProcessor implements ImageProcessor {
    @Override
    public String processImage(String imageBase64, Map<String,Object> args) {
        /*
        args格式
        {
            "rectangle":[
                {
                "top":95,
                "left":543,
                "width":12,
                "height":432
                },
                {
                ...其他方框
                }
            ]
        }
         */
        //怀念lua的自由,java确实桎梏有点大,想实现自由参数受类型安全限制
        String url = "https://aip.baidubce.com/rest/2.0/image-process/v1/inpainting";
        return queryURL(imageBase64,url,args);
    }
}
