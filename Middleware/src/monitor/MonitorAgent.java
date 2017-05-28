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
 *
 * @author guto
 */
public class MonitorAgent extends Thread{
 
    MonitorInterface monitor;
    float time;// time in seconds

    public MonitorAgent() {
    }

    public MonitorAgent(MonitorInterface monitor, float time) {
        this.monitor = monitor;
        this.time = time;
    }
    
    
    public void run(){
        
        while (true) {            
        
            sendStatusMachine(this.monitor.getStatusMachine());
            
            try {
                
                Thread.sleep((long) (this.time*1000));
            
            } catch (InterruptedException ex) {
                Logger.getLogger(MonitorAgent.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }
        
        
    }
    public void sendStatusMachine(StatusMachine sm){
        
        ClientRequestHandler crh = new ClientRequestHandler("localhost", 2424); // IP and port of  MonitorController
        Marshaller marshaller = new Marshaller();
        byte [] statusMsg;
        
        try {
        
            statusMsg  = marshaller.marshall(sm);
            crh.send(statusMsg);
            
        } catch (Exception e) {
        }
        
        
    }
    
    
}
