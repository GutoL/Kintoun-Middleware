/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.calculator;

/**
 *
 * @author guto
 */
public class Calculator implements ICalculator{

    @Override
    public float add(float a, float b) {
        return a + b;
    }

    @Override
    public float sub(float a, float b) {
        return a - b;
    }

    @Override
    public float mult(float a, float b) {
        return a * b;
    }

    @Override
    public float div(float a, float b) {
        return a / b;
    }
    
    
}
