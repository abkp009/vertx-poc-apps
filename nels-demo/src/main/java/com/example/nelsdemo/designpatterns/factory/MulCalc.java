package com.example.nelsdemo.designpatterns.factory;

public class MulCalc implements Calc {
    @Override
    public int calculate(int num1, int num2) {
        return num1 * num2;
    }
}
