package org.chock.shop.service;


import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/6 18:40
 * @description:
 */
@Slf4j
@Service
public class FileService {

    private static final String GOODS_IMAGE_PATH = "/files/goods/";

    public String uploadFile(MultipartFile multipartFile){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowStr = sdf.format(new Date());
        String originFileName = multipartFile.getOriginalFilename();
        String fileName = String.valueOf(new Date().getTime()) + originFileName.substring(originFileName.indexOf("."));
        String uploadPath = GOODS_IMAGE_PATH + nowStr + "/" + fileName;
        File file = new File(uploadPath);
        try {
            @Cleanup InputStream in  = multipartFile.getInputStream();
            @Cleanup OutputStream os = new FileOutputStream(file);
            // 得到文件流。以文件流的方式输出到新文件
            // 可以使用byte[] ss = multipartFile.getBytes();代替while
            byte[] buffer = new byte[2048];
            int n;
            while ((n = in.read(buffer,0,2048)) != -1){
                os.write(buffer,0,n);
            }
        }catch (IOException e){
            log.error("文件上传失败", e);
        }
        return "/group1/goods" + nowStr + "/" + fileName;
    }
}
