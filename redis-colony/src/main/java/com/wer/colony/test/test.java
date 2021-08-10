package com.wer.colony.test;

import com.wer.colony.model.ShopOrder;

import java.util.*;
import java.util.stream.Collectors;

public class test {

    public static void main(String[] args) {
        List<ShopOrder> list = new ArrayList<>();

        list.add(ShopOrder.builder().orderStatus(2).build());
        list.add(ShopOrder.builder().orderStatus(3).build());
        list.add(ShopOrder.builder().orderStatus(4).build());


        list.stream().forEach( shopOrder -> {
            System.out.println("升序排序前：" +shopOrder.getOrderId()+shopOrder.getOrderStatus() );
        });



        list.stream().forEach(shopOrder -> {
           for (int i = 1;i<4;i++) {
               shopOrder.setOrderId(Long.valueOf(i));
           }
        });


      //  List<ShopOrder> collect = list.stream().sorted(Comparator.comparing(u -> u.getOrderId())).collect(Collectors.toList());

        Collections.sort(list, new Comparator<ShopOrder>() {
            @Override
            public int compare(ShopOrder o1, ShopOrder o2) {
                if(o1.getOrderId()>o2.getOrderId())
                    return -1;
                else
                    return 0;
            }
        });

        list.stream().forEach( shopOrder -> {
            System.out.println("升序排序后：" +shopOrder.getOrderId()+shopOrder.getOrderStatus() );
        });

    }
}
