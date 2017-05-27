/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor;

/**
 *
 * @author guto
 */
public class ZabixFake {
    
    public float memoryConsumption;
    public float CPUConsumption;

    public ZabixFake() {
        this.CPUConsumption = 0;
        this.memoryConsumption = 0;
    }

    public ZabixFake(float memoryConsumption, float CPUConsumption) {
        this.memoryConsumption = memoryConsumption;
        this.CPUConsumption = CPUConsumption;
    }

    public float getCPUConsumption() {
       if(this.CPUConsumption <100){
            return (float) (CPUConsumption+(CPUConsumption*0.1));
       }
       else{
           return (float) 100;
       }
    }

    public float getMemoryConsumption() {
        if(this.memoryConsumption < 100){
            return (float) (memoryConsumption+(memoryConsumption*0.1));
        }
        else{
            return (float) 100;
        }
    }
    
    
    
    
}
