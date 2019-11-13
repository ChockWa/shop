package org.chock.shop.service;

import org.chock.shop.entity.Express;
import org.chock.shop.mapper.ExpressMapper;
import org.chock.shop.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/13 10:20
 * @description:
 */
@Service
public class ExpressService {
    @Autowired
    private ExpressMapper expressMapper;

    public String addExpress(String expressName, String expressNo){
        String id = UUIDUtils.getUuid();
        Express express = new Express();
        express.setExpressName(expressName);
        express.setExpressNo(expressNo);
        express.setId(id);
        express.setCreateTime(new Date());
        expressMapper.insert(express);
        return id;
    }
}
