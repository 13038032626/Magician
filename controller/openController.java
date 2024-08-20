package com.example.meitu2.controller;

import com.example.meitu2.pojos.images;
import com.example.meitu2.utils.Result;
import com.example.meitu2.utils.Time2StringUtils;
import com.example.meitu2.utils.innerCompressionUtils;
import com.example.meitu2.utils.photoOps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;

@RestController
@CrossOrigin
public class openController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    images images;

    @PostMapping("picture/open")
    public Result upload(MultipartFile file) throws IOException {
        System.out.println("到达！");
        String fileName = file.getOriginalFilename();  //获取文件原名
        BufferedImage bfi = ImageIO.read(file.getInputStream());
        System.out.println(fileName);

        byte[] bytes1 = innerCompressionUtils.compressToOneChannel(bfi);
        File file1 = new File(Time2StringUtils.generateFileName("dat"));
        OutputStream outputStream1 = new FileOutputStream(file1);
        outputStream1.write(bytes1);
        File file2 = new File(Time2StringUtils.generateFileName("dat"));
        OutputStream outputStream2 = new FileOutputStream(file2);
        outputStream2.write(photoOps.bfiToBytes(bfi));

        outputStream1.close();
        outputStream2.close();
        System.out.println("写入结束");


        images.setBfi(bfi);
        images.getAllSeriesBfi().add(bfi);
        images.setWidth(bfi.getWidth());
        images.setHeight(bfi.getHeight());
        images.setFileName(fileName);
        System.out.println("bean对象bfi："+bfi.toString());
        //画入静态文件中
        byte[] bytes = photoOps.bfiToBytes(bfi);
        File filele = new File(Time2StringUtils.generateFileName("png"));
        OutputStream outputStream = new FileOutputStream(filele);
        outputStream.write(bytes);
        return Result.ok("done");
    }
    @GetMapping("picture/image")
    public byte[] getImage() throws IOException {
        BufferedImage bfi = images.getBfi();
        return photoOps.bfiToBytes(bfi);
    }
    public String bfiToData(BufferedImage bfi){
        int width = bfi.getWidth();
        int height = bfi.getHeight();
        int[] data = new int[width*height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int rgb = bfi.getRGB(j, i);
                data[i*width+j] = rgb;
            }
        }
        return Arrays.toString(data);
    }
}

