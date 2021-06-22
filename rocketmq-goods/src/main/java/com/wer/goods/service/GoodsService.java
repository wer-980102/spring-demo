package com.wer.goods.service;

public interface GoodsService {

    int updateGoods(long orderId,long goodsId,int goodsNumber);

    int CancelupdateGoods(long orderId, long goodsId, int goodsNumber);

}
