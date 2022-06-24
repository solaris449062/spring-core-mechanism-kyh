package com.hello.core.singleton;

public class StatefulService {

   private int price; // this is a stateful field

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
//        this.price = price; // this can cause problem!
        return price;
    }

//    public int getPrice() {
//        return price;
//    }
}
