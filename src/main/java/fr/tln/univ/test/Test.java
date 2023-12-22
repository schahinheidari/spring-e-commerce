package fr.tln.univ.test;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        List<Apple> appleList = new ArrayList<>();
        Apple apple = new Apple();
        apple.setColor("red");
        apple.setWeight(10);
        appleList.add(apple);

        appleList.add(new Apple("yelow", 55));

        for (Apple a : appleList) {
            System.out.println(a.getColor() + " " + a.getWeight());
        }
    }

}
