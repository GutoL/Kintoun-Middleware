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
    String nameMachine;
    ClientRequestHandler crh;
        

    public MonitorAgent() {
    }

    public MonitorAgent(String name){
        this.nameMachine = name;
    }
    
    public MonitorAgent(MonitorInterface monitor, float time) {
        this.monitor = monitor;
        this.time = time;
        
    }
    
    
    public void run(){
        
        
        
        while (true) {            
        
            System.out.println("Send status to controller...");
            sendStatusMachine(this.monitor.getStatusMachine());
            System.out.println("Sended status to controller...");
            
            try {
                
                Thread.sleep((long) (this.time*1000));
            
            } catch (InterruptedException ex) {
                Logger.getLogger(MonitorAgent.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }
        
        
    }
    public void sendStatusMachine(StatusMachine sm){
        crh = new ClientRequestHandler("localhost", 2425); // IP and port of  MonitorController
        Marshaller marshaller = new Marshaller();
        byte [] statusMsg;
        
        try {
        
            sm.nameMachine = this.nameMachine;
            statusMsg  = marshaller.marshall(sm);
            System.out.println("IP: "+crh.getHost()+" porta: "+crh.getPort());
            crh.send(statusMsg, true);
            
        } catch (Exception e) {
          
            e.printStackTrace();
            
        }
        
        
    }
    
    
}
