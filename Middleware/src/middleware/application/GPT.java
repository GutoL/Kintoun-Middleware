/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.application;

import monitor.MonitorController;

/**
 *
 * @author guto
 */
public class GPT {
        
    public static void main(String args[]){
        
        MonitorController controller = new MonitorController(2424, (float) 0.8);
        System.out.println("GPT is ready!");
        controller.manage();
        
        
    }
    
    
}
