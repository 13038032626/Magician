package com.example.meitu2.controller.aiFunc;

import com.example.meitu2.controller.aiFunc.processor.ImageProcessor;
import jakarta.websocket.OnClose;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController()
@RequestMapping("baidu")
public class AIController {
    @PostMapping("/imageProcess")
    public String imageProcess(String type, String imageData, Map<String, Object> args){
        ImageProcessor processor = ImageProcessorFactory.getProcessor(type);
        return processor.processImage(imageData,args);
    }
}
