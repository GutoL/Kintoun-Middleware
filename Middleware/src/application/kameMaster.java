/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package application;

import monitor.MonitorController;

/**
 * Example of manager using middleware.
 * @author guto
 */
public class kameMaster {
        
    public static void main(String args[]){
        
        MonitorController controller = new MonitorController(2424); // limit: 80%
        System.out.println("GPT is ready!");
        
        controller.manage();
        
        
    }
    
    
}
