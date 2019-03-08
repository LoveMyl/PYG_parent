package cn.itcast.core.pojo.entity;

import java.io.Serializable;


public class Sale implements Serializable {
    //商品分类
    private String categary;
    //商品分类数量
    private int total;

    public String getCategary() {
        return categary;
    }

    public void setCategary(String categary) {
        this.categary = categary;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    @Override
    public String toString() {
        return "Sale{" +
                "categary='" + categary + '\'' +
                ", total=" + total +
                '}';
    }
}
