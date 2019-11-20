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
    private static final String GOODS_IMAGE_PATH_WIN = "e:\\goods\\";

    public String uploadFile(MultipartFile multipartFile){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowStr = sdf.format(new Date());
        String originFileName = multipartFile.getOriginalFilename();
        String fileName = String.valueOf(System.currentTimeMillis()) + originFileName.substring(originFileName.indexOf("."));
        String uploadPath = GOODS_IMAGE_PATH + nowStr + "/" + fileName;
        File file = new File(uploadPath);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            @Cleanup InputStream in  = multipartFile.getInputStream();
            @Cleanup OutputStream os = new FileOutputStream(file);
            // 得到文件流。以文件流的方式输出到新文件
            // 可以使用byte[] ss = multipartFile.getBytes();代替while
            byte[] buffer = new byte[2048];
            int n;
            while ((n = in.read(buffer,0,2048)) != -1){
                os.write(buffer,0,n);
            }
            return "/group1/goods/" + nowStr + "/" + fileName;
        }catch (IOException e){
            log.error("文件上传失败", e);
        }
        return null;
    }

    public void deleteFile(String path){
        // 因為路徑中都帶有group1，所以替換成要files
        path = path.replace("group1","files");
        File file = new File(path);
        if(file.exists()){
            file.delete();
        }
    }

    public String uploadFileWin(MultipartFile multipartFile){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowStr = sdf.format(new Date());
        String originFileName = multipartFile.getOriginalFilename();
        String fileName = String.valueOf(System.currentTimeMillis()) + originFileName.substring(originFileName.indexOf("."));
        String uploadPath = GOODS_IMAGE_PATH_WIN + nowStr + "\\" + fileName;
        File file = new File(uploadPath);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            @Cleanup OutputStream os = new FileOutputStream(file);
            // 得到文件流。以文件流的方式输出到新文件
            byte[] bytes = multipartFile.getBytes();
//            byte[] buffer = new byte[2048];
//            int n;
            os.write(bytes);
//            while ((n = in.read(buffer,0,2048)) != -1){
//                os.write(buffer,0,n);
//            }
            return "e:\\goods\\" + nowStr + "\\" + fileName;
        }catch (IOException e){
            log.error("文件上传失败", e);
        }
        return null;
    }

    public void deleteFileWin(String path){
        // 因為路徑中都帶有group1，所以替換成要files
        File file = new File(path);
        if(file.exists()){
            file.delete();
        }
    }
}
