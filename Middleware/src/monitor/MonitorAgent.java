/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor;

import java.util.logging.Level;
import java.util.logging.Logger;
import middleware.client.ClientRequestHandler;
import middleware.util.Marshaller;

/**
 * Monitor that running in instance and send current stat of machine to manager.
 * @author guto
 */
public class MonitorAgent extends Thread{
 
    MonitorInterface monitor;
    float time;// time in seconds
    //String nameMachine;
    ClientRequestHandler crh;
    String GPTIP;
        

    public MonitorAgent() {
    }

    
    public MonitorAgent(MonitorInterface monitor, float time) {
        this.monitor = monitor;
        this.time = time;
        this.GPTIP = GetInfo.getInstance().gptIP;
    }
    
    
    public void run(){
        
        
        
        while (true) {            
            
            sendStatusMachine(this.monitor.getStatusMachine());
            
            try {
                
                Thread.sleep((long) (this.time*5000));
            
            } catch (InterruptedException ex) {
                Logger.getLogger(MonitorAgent.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }
        
        
    }
    
    /**
     * Method that send information to manager.
     */
    public void sendStatusMachine(StatusMachine sm){
        
        crh = new ClientRequestHandler(this.GPTIP, 2425); // IP and port of  MonitorController
        Marshaller marshaller = new Marshaller();
        byte [] statusMsg;
        
        try {
        
            
            System.out.println("send (%): "+sm.CPUConsumption);
            statusMsg  = marshaller.marshall(sm);
            
            crh.send(statusMsg, true);
            
            
            
        } catch (Exception e) {
          
            e.printStackTrace();
            
        }
        
        
    }
    
    
}
