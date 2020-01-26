package org.chock.shop.service;

import org.chock.shop.entity.Category;
import org.chock.shop.mapper.CategoryMapper;
import org.chock.shop.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public void add(Category category){
        category.setId(UUIDUtils.getUuid());
        category.setStatus(1);
        categoryMapper.insert(category);
    }

    public void update(Category category){
        categoryMapper.updateById(category);
    }

    public void delete(String categoryId){
        categoryMapper.deleteById(categoryId);
    }

    public List<Category> list(){
        return categoryMapper.selectList(null);
    }


}
