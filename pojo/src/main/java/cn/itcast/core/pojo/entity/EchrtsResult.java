package cn.itcast.core.pojo.entity;

import java.io.Serializable;

public class EchrtsResult implements Serializable {
    private String day;
    private String price;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}


