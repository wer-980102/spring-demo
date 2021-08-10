package com.wer.designpattern.ocp;

/**
 * 开闭原则
 */
public class JavaDiscountCourse extends  JvaaCourse{

    public JavaDiscountCourse(Integer id, String name, Double price) {
        super(id, name, price);
    }

    public Double getOringPrice(){
        return  super.getprice();
    }


    public Double getPrice(){
        return  super.getprice() * 0.61;
    }
}
