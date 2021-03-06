package org.chock.shop.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.chock.shop.constant.Constant;
import org.chock.shop.dto.PageParam;
import org.chock.shop.dto.PageResult;
import org.chock.shop.entity.Brand;
import org.chock.shop.mapper.BrandMapper;
import org.chock.shop.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/6 15:27
 * @description:
 */
@Service
public class BrandService {
    @Autowired
    private BrandMapper brandMapper;

    public void add(Brand brand){
        brand.setId(UUIDUtils.getUuid());
        brand.setStatus(1);
        brandMapper.insert(brand);
    }

    public void update(Brand brand){
        if(StringUtils.isBlank(brand.getId())){
            throw new IllegalArgumentException("id不能为空");
        }
        brandMapper.updateById(brand);
    }

    public PageResult<Brand> listPage(PageParam pageParam){
        Page<Brand> page = new Page<>(pageParam.getPageIndex(), pageParam.getPageSize());
        brandMapper.selectPage(page, null);
        page.getRecords().forEach(e -> {
            e.setLogo(Constant.DNS_HTTPS + e.getLogo());
        });

        PageResult<Brand> result = new PageResult<>();
        result.setTotal(page.getTotal());
        result.setRecords(page.getRecords());
        return result;
    }

    public List<Brand> list(){
        return brandMapper.selectList(null);
    }

    public void delete(String brandId){
        brandMapper.deleteById(brandId);
    }
}
