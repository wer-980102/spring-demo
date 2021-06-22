package com.wer.goods.service;

import com.wer.goods.dao.ShopGoodsMapper;
import com.wer.goods.dao.shopGoodsUniqueMapper;
import com.wer.goods.model.ShopGoods;
import com.wer.goods.model.shopGoodsUnique;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 扣减库存
 */
@Service
public class GoodsServiceImpl implements GoodsService{
    private static final Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Autowired
    private ShopGoodsMapper shopGoodsMapper;
    @Autowired
    private shopGoodsUniqueMapper goodsUniqueMapper;
    /**
     * 扣减库存
     * 1、这个多不安全--所以加锁
     * 2、幂等性问题
     * @param goodsId
     * @param goodsNumber
     * @return
     */
    @Override
    public synchronized int updateGoods(long orderId,long goodsId, int goodsNumber) {

        try{
            shopGoodsUnique shopGoodsUnique = new shopGoodsUnique();
            shopGoodsUnique.setOrderId(orderId);
            goodsUniqueMapper.insert(shopGoodsUnique);//如果这个地方消息重复了\异常
        }catch (Exception e){//如果异常了呢\
            logger.error("重复的修改库存信息：[" + orderId + "]");
            //完美的 又写一个topic 的数据（重复的），这个数据 可以用来分析
            return 1;
        }
        //判断是否有该库存
        ShopGoods shopGoods = shopGoodsMapper.selectByPrimaryKey(goodsId);
        if(null == shopGoods) return -1;
        Integer goodnumber = shopGoods.getGoodsNumber() - goodsNumber;
        shopGoods.setGoodsNumber(goodnumber);
        if(shopGoodsMapper.updateByPrimaryKey(shopGoods)>0){
            return 1;
        }else{
            logger.error("修改库存失败：[" + orderId + "]");
            return -1;
        }
    }

    //回退-扣减库存
    public  int CancelupdateGoods(long orderId, long goodsId, int goodsNumber){
        try {
            //去重表中有，才能证明是插入了，所以要回退
            shopGoodsUnique shopGoodsUnique = new shopGoodsUnique();
            shopGoodsUnique.setOrderId(orderId);
            goodsUniqueMapper.insert(shopGoodsUnique);
        }catch (Exception e) {
            ShopGoods shopGoods =shopGoodsMapper.selectByPrimaryKey(goodsId);
            Integer goodnumber = shopGoods.getGoodsNumber()+goodsNumber;
            shopGoods.setGoodsNumber(goodnumber);
            if(shopGoodsMapper.updateByPrimaryKey(shopGoods)>=0){
                //logger.info("修改库存成功：[" + orderId + "]");
                return 1;
            }else{
                logger.error("回退库存失败：[" + orderId + "]");
                return -1;
            }
        }
        return 1;
    }

}
