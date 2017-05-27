/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.calculator;

/**
 *
 * @author demis
 */
public class CalculatorHelper {
    
    public String calc(String[] datas, Calculator calculator){
        String response="NaN";
        if(datas[1].equals("+")){
                 response = Float.toString(calculator.add(Float.parseFloat(datas[0]), Float.parseFloat(datas[2])));
             }
             else if(datas[1].equals("-")){
                 response = Float.toString(calculator.sub(Float.parseFloat(datas[0]), Float.parseFloat(datas[2])));
             }
             else if(datas[1].equals("*")){
                 response = Float.toString(calculator.mult(Float.parseFloat(datas[0]), Float.parseFloat(datas[2])));
             }
             else if(datas[1].equals("/")){
                 response = Float.toString(calculator.div(Float.parseFloat(datas[0]), Float.parseFloat(datas[2])));
             }
        return response;
    }
    
    public String calc(String operation, Calculator calculator){
        
        String[] datas = null;
        
        if(operation.contains("+")){
            
            datas = operation.split("\\+");
            return Float.toString(calculator.add(Float.parseFloat(datas[0]), Float.parseFloat(datas[1])));
        }
        
        
        else if(operation.contains("-")){
            
            datas = operation.split("\\-");
            return Float.toString(calculator.sub(Float.parseFloat(datas[0]), Float.parseFloat(datas[1])));
        }
        
        else if(operation.contains("*")){
        
            datas = operation.split("\\*");
            return Float.toString(calculator.mult(Float.parseFloat(datas[0]), Float.parseFloat(datas[1])));
            
        }
        
        else if(operation.contains("/")){
            
            
            datas = operation.split("\\/");
            return Float.toString(calculator.div(Float.parseFloat(datas[0]), Float.parseFloat(datas[1])));
        }
        
        else{
            return "NaN";
        }
        
    }
    
}
