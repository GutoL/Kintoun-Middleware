/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import monitor.MonitorController;
import monitor.api.InstanceServices;
import monitor.api.TokenRequestor;

/**
 *
 * @author guto
 */
public class GPT {
        
    public static void main(String args[]){
        
        MonitorController controller = new MonitorController(2424, (float) 80); // limit: 80%
        System.out.println("GPT is ready!");
        
        controller.manage();
        
        
    }
    
    
}
