package com.example.nelsdemo.designpatterns.factory;

public class CalcFactory {
    public static Calc getCalc(String type) {
        switch (type) {
            case "add":
                return new AddCalc();
            case "mul":
                return new MulCalc();
            default:
                return null;
        }
    }
}
