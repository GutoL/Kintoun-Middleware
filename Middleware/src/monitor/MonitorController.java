/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor;

import middleware.server.ServerRequestHandler;
import middleware.util.Marshaller;

/**
 *
 * @author guto
 */
public class MonitorController {
    
        private int port;
        LimitResourcesMachine limitResourcesMachine;
        

        public MonitorController() {
        }

    public MonitorController(int port,float limit) {
        this.port = 2425;
        this.limitResourcesMachine = new LimitResourcesMachine(limit, limit);
    }
        
       
    
    public void manage(){
        
        ServerRequestHandler srh = new ServerRequestHandler(this.port);
        StatusMachine statusMachine;
        Marshaller marshaller = new Marshaller();
       
        
        try {
        
            while (true) {            
            
                System.out.println("Wait requisitions... porta: "+srh.getPortNumber());
                byte [] msg = srh.receive(true);
                System.out.println("Recieve!!!!"); 
                statusMachine = (StatusMachine) marshaller.unmarshall(msg);
                 ProcessStatusMachine processStatusMachine = new ProcessStatusMachine(statusMachine, limitResourcesMachine);
                 processStatusMachine.start();
                 
            }   
            
            
        } catch (Exception e) {
        }
        
        
    }
    
}
