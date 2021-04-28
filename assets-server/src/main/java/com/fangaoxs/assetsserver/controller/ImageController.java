package com.fangaoxs.assetsserver.controller;

import com.fangaoxs.assetsserver.utils.FileUtils;
import com.fangaoxs.assetsserver.vo.Image;
import com.fangaoxs.commonserver.response.ResultResponse;
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

    @PostMapping("/insertEngineer")
    public ResultResponse imgInsert(@RequestParam("image")MultipartFile image){
        log.info("图片原名称[{}]，图片的格式[{}]，图片的大小[{}]，图片是否为空[{}]",
                image.getOriginalFilename(),
                image.getContentType(),
                FileUtils.getFileSize(image),
                image.isEmpty()?"空文件":"不是空文件");
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
