package com.example.nelsdemo.designpatterns.factory;

public class TestCalc {
    public static void main(String[] args) {
        Calc c  = CalcFactory.getCalc("add");
        System.out.println(c.calculate(1,2));
        c  = CalcFactory.getCalc("mul");
        System.out.println(c.calculate(1,2));
    }
}
