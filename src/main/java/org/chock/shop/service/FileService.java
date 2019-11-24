package org.chock.shop.service;


import com.alibaba.fastjson.JSON;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.chock.shop.dto.SmmsResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/6 18:40
 * @description:
 */
@Slf4j
@Service
public class FileService {

    private static final String GOODS_IMAGE_PATH = "/files/goods/";
    private static final String GOODS_IMAGE_PATH_WIN = "D:\\chockwa-projects\\front\\shop-mgmt\\src\\views\\brand\\images\\";
    @Autowired
    private RestTemplate restTemplate;
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
        String uploadPath = GOODS_IMAGE_PATH_WIN  + fileName;
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
            return GOODS_IMAGE_PATH_WIN + fileName;
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

    public String uploadImage(MultipartFile file) {
        try {
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("multipart/form-data");
            headers.setContentType(type);
            headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
            //图片参数
            ByteArrayResource resource = new ByteArrayResource(file.getBytes()){
                @Override
                public String getFilename() {
                    return UUID.randomUUID().toString() + ".jpg";
                }
            };
            MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
            form.add("smfile", resource);
            //用HttpEntity封装整个请求报文
            HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(form, headers);
            String result = restTemplate.postForObject("https://sm.ms/api/v2/upload", files, String.class);
            SmmsResp smmsResp = JSON.parseObject(result, SmmsResp.class);
            if(smmsResp.getMessage().contains("this image exists at")){
                String message = smmsResp.getMessage();
                return smmsResp.getMessage().substring(message.indexOf("https://"), message.length());
            }
            return smmsResp.getData().getUrl();
        }catch (Exception e) {
            log.error("上传失败", e);
        }
        return null;
//        try {
//            SmMsUploadResponseDto smMsUploadResponseDto = MAPPER.readValue(result, SmMsUploadResponseDto.class);
//            if (smMsUploadResponseDto.getSuccess()) {
//                return smMsUploadResponseDto.getData().getUrl();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
