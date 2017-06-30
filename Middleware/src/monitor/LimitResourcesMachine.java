/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor;

/**
 * Class that contains instance limits.
 * @author guto
 */
public class LimitResourcesMachine {
    
   private float limitCPU;
   private float limitMemory;

    public LimitResourcesMachine() {
    }

    public LimitResourcesMachine(float limitCPU, float limitMemory) {
        this.limitCPU = limitCPU;
        this.limitMemory = limitMemory;
    }

    public void setLimitCPU(float limitCPU) {
        this.limitCPU = limitCPU;
    }

    public void setLimitMemory(float limitMemory) {
        this.limitMemory = limitMemory;
    }

    public float getLimitCPU() {
        return limitCPU;
    }

    public float getLimitMemory() {
        return limitMemory;
    }
    
    
    
}
