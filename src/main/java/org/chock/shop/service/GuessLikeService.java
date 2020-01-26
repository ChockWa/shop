package org.chock.shop.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.chock.shop.dto.GoodsDto;
import org.chock.shop.dto.GoodsInfoItem;
import org.chock.shop.dto.GoodsInfoItemQuery;
import org.chock.shop.entity.GuessLike;
import org.chock.shop.exception.BizException;
import org.chock.shop.mapper.GoodsMapper;
import org.chock.shop.mapper.GuessLikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/14 10:42
 * @description:
 */
@Service
public class GuessLikeService {

    @Autowired
    private GuessLikeMapper guessLikeMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    public List<GoodsDto> list(){
        List<GuessLike> likes = guessLikeMapper.selectList(null);
        if(CollectionUtils.isEmpty(likes)){
            return null;
        }
        Page<GoodsDto> page = new Page<>(1, likes.size());
        return goodsMapper.listGoodsPage(page, likes.stream().map(GuessLike::getGoodsId).collect(Collectors.toList())).getRecords();
    }

    public void add(String goodsId){
        GuessLike guessLike = guessLikeMapper.selectOne(Wrappers.<GuessLike>lambdaQuery().eq(GuessLike::getGoodsId,goodsId));
        if(guessLike != null){
            throw BizException.GOODS_EXIST_GUESS_LIKE_ERROR;
        }
        guessLike = new GuessLike();
        guessLike.setGoodsId(goodsId);
        guessLike.setCreateTime(new Date());
        guessLikeMapper.insert(guessLike);
    }

    public void delete(String goodsId){
        guessLikeMapper.delete(Wrappers.<GuessLike>lambdaQuery().eq(GuessLike::getGoodsId,goodsId));
    }

    /**
     * 获取用户端猜你喜欢列表
     * @return
     */
    public List<GoodsInfoItem> getGuessLikeGoodsItems(){
        List<GuessLike> likes = guessLikeMapper.selectList(null);
        GoodsInfoItemQuery query = new GoodsInfoItemQuery();
        query.setGoodsIds(likes.stream().map(GuessLike::getGoodsId).collect(Collectors.toList()));
        Page<GoodsInfoItem> page = new Page<>(1, likes.size());
        goodsMapper.getGoodsInfoItemsPage(page, query);
        return page.getRecords();
    }
}
