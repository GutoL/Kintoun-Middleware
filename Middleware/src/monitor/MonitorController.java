/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import middleware.server.ServerRequestHandler;
import middleware.util.Marshaller;

/**
 *
 * @author guto
 */
public class MonitorController {
    
        private int port;
        LimitResourcesMachine limitResourcesMachine;
        ArrayList<MachineInformation> machines = new ArrayList<>(); // como preencher essa lista?
        

        public MonitorController() {
        }

    public MonitorController(int port,float limit) {
        this.port = 2425;
        this.limitResourcesMachine = new LimitResourcesMachine(limit, limit);
    }
        
    /**
     * This method will discover machines running in Openstack
     */
    public void getListMachines(){
        
        ArrayList<String> machines = new ArrayList<>();
        
        try {
            
            Runtime rt = Runtime.getRuntime();
            //String[] commands = {"system.exe","-get t"};
            String commands = "./src/monitor/Shell/listInstances.sh";
            //commands = "ifconfig";
            Process proc = rt.exec(commands);

            BufferedReader stdInput = new BufferedReader(new 
                 InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new 
                 InputStreamReader(proc.getErrorStream()));

            // read the output from the command
            String s = null;
            int lines = 0;
            while ((s = stdInput.readLine()) != null) {
                //System.out.println(s);
                if(lines >=3){ // ignore header
                    machines.add(s);
                }
                lines++;
            }
            machines.remove((machines.size()-1)); // ignore footer
            //machines.remove((machines.size()-1));
            
            int index;
            String [] data;
            MachineInformation machineInformation = new MachineInformation();
            for (int i = 0; i < machines.size(); i++) {
                //System.out.println(machines.get(i));
                //index = machines.get(i).indexOf("private=");
                //System.out.println(machines.get(i).substring(index+8, index+16));
                
                data = machines.get(i).split("\\|");
                
                System.out.println(data[2].substring(1, data[2].length()));// name
                //System.out.println(data[6]);// IP
                
                
                index = data[6].indexOf("private=");
                System.out.println(data[6].substring(index+8,data[6].length()));// IP
                
                machineInformation.setIP(data[6].substring(index+8, data[6].length()));
                machineInformation.setName(data[2].substring(1, data[2].length()));
                machineInformation.setPort(1010); // essa seria a porta padrão que os servidores ficariam escutando =/
                this.machines.add(machineInformation);
                
                
            }
            
            
            
            // read any errors from the attempted command
            while ((s = stdError.readLine()) != null) {
                
                System.out.println(s);
                
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    
    
    
    public void manage(){
        
        ServerRequestHandler srh = new ServerRequestHandler(this.port);
        StatusMachine statusMachine;
        Marshaller marshaller = new Marshaller();
       
        
        try {
        
            while (true) {            
                
                this.getListMachines();
                
                System.out.println("Wait requisitions..... porta: "+srh.getPortNumber());
                byte [] msg = srh.receive(true);
                System.out.println("Recieve!!!!"); 
                statusMachine = (StatusMachine) marshaller.unmarshall(msg);
                 ProcessStatusMachine processStatusMachine = new ProcessStatusMachine(statusMachine, limitResourcesMachine,machines);
                 processStatusMachine.start();
                 // tem que ter o ip da máquina que vai pausar, o ip da máquina que vai iniciar para fazer o rebind no 
                 // servidor de nomes, tem que ter o nome das duas máquinas também, para o devstack pausar uma e despausar
                 // a outra (Show details of instance: openstack server show NAME)
                 
            }   
            
            
        } catch (Exception e) {
        }
        
        
    }
    
}
