/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor;

import java.util.Random;

/**
 *
 * @author guto
 */
public class ZabixFake implements MonitorInterface {
    
    public float memoryConsumption;
    public float CPUConsumption;
    public float rate;
    public StatusMachine sm;

    public ZabixFake() {
        this.CPUConsumption = 0;
        this.memoryConsumption = 0;
        this.rate = (float)0.2;
    }

    public ZabixFake(float memoryConsumption, float CPUConsumption) {
        this.memoryConsumption = memoryConsumption;
        this.CPUConsumption = CPUConsumption;
    }

    public float getCPUConsumption() {
       if(this.CPUConsumption <100){
            return (float) (this.CPUConsumption+(this.CPUConsumption*0.1));
       }
       else{
           return (float) 100;
       }
    }

    public float getMemoryConsumption() {
        if(this.memoryConsumption < 100){
            return (float) (this.memoryConsumption+(this.memoryConsumption*0.1));
        }
        else{
            return (float) 100;
        }
    }

    @Override
    public StatusMachine getStatusMachine() {
        if(CPUConsumption < 100 && memoryConsumption < 100){
            
            /*
            this.memoryConsumption = this.memoryConsumption + (rate*100);
            this.CPUConsumption = this.CPUConsumption + (rate*100);
            System.out.println("CPU: "+this.CPUConsumption+" memory: "+this.memoryConsumption);
            */
            
            Random generator = new Random();
            this.memoryConsumption = generator.nextInt(100);
            this.CPUConsumption = generator.nextInt(100);
            
            
        }
        this.sm = new StatusMachine(this.memoryConsumption, this.CPUConsumption);
        return this.sm;
    }
    
    
    
    
}
