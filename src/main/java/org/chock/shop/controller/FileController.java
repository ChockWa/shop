package org.chock.shop.controller;

import org.chock.shop.dto.Result;
import org.chock.shop.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("upload")
    public Result uploadFile(MultipartFile file){
        return Result.SUCCESS().setData("path", fileService.uploadFile(file));
    }
}
