package com.example.meitu2.controller.aiFunc;

import com.example.meitu2.controller.aiFunc.processor.ImageProcessor;
import com.example.meitu2.controller.aiFunc.processor.processorImpl.ClarityEnhancementProcessor;
import com.example.meitu2.controller.aiFunc.processor.processorImpl.InpaintingProcessor;
import com.example.meitu2.controller.aiFunc.processor.processorImpl.LossLessEnlargementProcessor;
import com.example.meitu2.controller.aiFunc.processor.processorImpl.RecognitionProcessor;

public class ImageProcessorFactory {
    public static ImageProcessor getProcessor(String type){
        switch (type){
            case "clarityEnhancement":
                return new ClarityEnhancementProcessor();
            case "lossLessEnlargement":
                return new LossLessEnlargementProcessor();
            case "inpaintingProcessor":
                return new InpaintingProcessor();
            case "recognitionProcessor":
                return new RecognitionProcessor();
            default:
                throw new IllegalArgumentException("参数未知 or 未支持");
        }
    }
}
