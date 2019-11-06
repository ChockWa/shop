package org.chock.shop.controller;

import org.chock.shop.dto.Result;
import org.chock.shop.entity.Brand;
import org.chock.shop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/6 15:33
 * @description:
 */
@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @PostMapping("add")
    public Result add(@RequestBody Brand brand){
        brandService.add(brand);
        return Result.SUCCESS();
    }

    @PostMapping("update")
    public Result update(@RequestBody Brand brand){
        brandService.update(brand);
        return Result.SUCCESS();
    }

    @GetMapping("list")
    public Result list(){
        return Result.SUCCESS().setData("list", brandService.list());
    }

    @GetMapping("del")
    public Result del(String brandId){
        brandService.delete(brandId);
        return Result.SUCCESS();
    }

}
