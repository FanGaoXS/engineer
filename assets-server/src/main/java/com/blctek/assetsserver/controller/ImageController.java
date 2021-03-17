package com.blctek.assetsserver.controller;

import com.blctek.assetsserver.utils.FileUtils;
import com.blctek.assetsserver.vo.Image;
import com.blctek.commonserver.response.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
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

    @PostMapping("/insertEngineer")
    public ResultResponse imgInsert(@RequestParam("file")MultipartFile image){
        log.info("图片的原名称->[{}]",image.getOriginalFilename());
        log.info("图片的格式->[{}]",image.getContentType());
        log.info("图片的大小->[{}]",FileUtils.getFileSize(image));
        log.info("图片是否为空->[{}]",image.isEmpty()?"空文件":"不是空文件");
        String uuid = UUID.randomUUID().toString();//生成uuid来作为图片名
        String path = FileUtils.uploadFile(image, "/image/engineer/", uuid);
        log.info("图片上传后的路径->[{}]",path);
        Image resultImage = new Image();
        resultImage.setUuid(uuid);
        return new ResultResponse()
                .setMessage("插入工程用具的图片")
                .setData(resultImage);//返回该图片的uuid
    }
}
