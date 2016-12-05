package com.example.benson.foodpacker2;

/**
 * Created by Benson on 2016/11/29.
 */
public class Product {
    // 商品名稱
    private String name;

    // 商品數量
    private  int num;
    // 該商品總價
    private  int price;

    @Override
    public String toString() {
        return  " Product{ " +
                " name=' " + name + "\' " +
                " , num= " + num +
                " , price= " + price +
                "} " ;
    }

    public  void setName(String title, String name) {
        this.name = name;
    }


    public  void setNum( int num) {
        this.num = num;
    }

    public  void setPrice( int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }


    public  int getNum() {
        return num;
    }

    public  int getPrice() {
        return price;
    }
}
