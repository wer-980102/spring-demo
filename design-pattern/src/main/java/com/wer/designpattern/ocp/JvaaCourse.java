package com.wer.designpattern.ocp;

public class JvaaCourse  implements  ICourse{
    private Integer Id;
    private String name;
    private Double price;

    public JvaaCourse(Integer id, String name, Double price) {
        Id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public Integer getId() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Double getprice() {
        return null;
    }
}
