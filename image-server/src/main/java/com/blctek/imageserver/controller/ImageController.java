package com.blctek.imageserver.controller;

import com.blctek.imageserver.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/01/13/12:46
 * @Description:
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/img")
@Slf4j
public class ImageController {

    @PostMapping("/imgInsert")
    public String imgInsert(@RequestParam("file")MultipartFile image){
        log.info("图片的原名称->[{}]",image.getOriginalFilename());
        log.info("图片的格式->[{}]",image.getContentType());
        log.info("图片的大小->[{}]",FileUtils.getFileSize(image));
        log.info("图片是否为空->[{}]",image.isEmpty()?"空文件":"不是空文件");
        //生成uuid来作为图片名
        String imageName = UUID.randomUUID().toString();
        String imagePath = FileUtils.uploadFile(image, "/image/car/", imageName);
        log.info("图片上传后的路径->[{}]",imagePath);
        return imageName;
    }
}
