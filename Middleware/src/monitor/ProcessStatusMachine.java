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
public class ProcessStatusMachine extends Thread{
    
    StatusMachine statusMachine;
    LimitResourcesMachine limitResourcesMachine;

    public ProcessStatusMachine() {
    }

    public ProcessStatusMachine(StatusMachine statusMachine,LimitResourcesMachine limitResourcesMachine) {
        this.statusMachine = statusMachine;
        this.limitResourcesMachine = limitResourcesMachine;
        
    }
    
       
    public void run(){
        
        if((statusMachine.CPUConsumption >= this.limitResourcesMachine.getLimitCPU()) ||
                (statusMachine.memoryConsumption>=this.limitResourcesMachine.getLimitMemory())){
            
           System.out.println("Status CPU: "+this.statusMachine.CPUConsumption);
            System.out.println("Status Memory: "+this.statusMachine.CPUConsumption);
            
            // aqui dá o comando para o devstack para a máquina
            System.out.println("Stop machine: "+statusMachine.nameMachine);
        }
        
    }
    
}
