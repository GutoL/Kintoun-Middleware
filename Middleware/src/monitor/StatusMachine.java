/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor;

import java.io.Serializable;

/**
 *
 * @author guto
 */
public class StatusMachine implements Serializable {
    
    public float memoryConsumption;
    public float CPUConsumption;
    public String nameMachine;

    public StatusMachine(float memoryConsumption, float CPUConsumption) {
        this.memoryConsumption = memoryConsumption;
        this.CPUConsumption = CPUConsumption;
        
    }

    public StatusMachine() {
    }
    
    
    
    
}
