package org.chock.shop.controller;

import org.chock.shop.dto.Result;
import org.chock.shop.entity.Category;
import org.chock.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public Result categories(){
        return Result.SUCCESS().setData("list", categoryService.list());
    }

    @PostMapping("/add")
    public Result add(@RequestBody Category category){
        categoryService.add(category);
        return Result.SUCCESS();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Category category){
        categoryService.update(category);
        return Result.SUCCESS();
    }

    @GetMapping("/del")
    public Result del(String categoryId){
        categoryService.delete(categoryId);
        return Result.SUCCESS();
    }
}
