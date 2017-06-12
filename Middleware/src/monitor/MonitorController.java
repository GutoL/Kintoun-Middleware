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
import monitor.api.InstanceServices;
import monitor.api.TokenRequestor;

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
            //String commands = "./src/monitor/Shell/listInstances.sh";
            String commands = "./Shell/listInstances.sh";
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
                if((lines >=4)&&(s.contains("="))){ // ignore header
                   machines.add(s);
                   System.out.println("he"+s);
                }
                lines++;
            }
            //machines.remove((machines.size()-1)); // ignore footer
            //machines.remove((machines.size()-1));
            
            int index;
            String [] data;
            MachineInformation machineInformation;
            //System.out.println("numero de maquinas: "+machines.size());
            for (int i = 0; i < machines.size(); i++) {
                //System.out.println(machines.get(i));
                //index = machines.get(i).indexOf("private=");
                //System.out.println(machines.get(i).substring(index+8, index+16));
                machineInformation = new MachineInformation();
                data = machines.get(i).split("\\|");
                
                //System.out.println(data[2].substring(1, data[2].length()));// name
                //System.out.println(data[6]);// IP
                
                //System.out.println(data[4]);
                //index = data[6].indexOf("private=");
                index = data[4].indexOf("=");
                //System.out.println("IP: "+data[4].substring(index+1,(data[4].length()-2)));
                //System.out.println("Name: "+data[2]);
                
                machineInformation.setIP(data[4].substring(index+1,(data[4].length()-2)));
                machineInformation.setName(data[2]);
                //System.out.println("se liga: "+machineInformation.IP);
                //machineInformation.setName(data[2].substring(1, data[2].length()));
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
        
            //this.getListMachines();
            TokenRequestor tr=new TokenRequestor();
            String token=tr.request();
            InstanceServices instanceServices=new InstanceServices();
            instanceServices.setToken(token);
            this.machines=instanceServices.getServers();
        
            while (true) {            
                
                
                
                System.out.println("Wait requisitions..... port: "+srh.getPortNumber());
                byte [] msg = srh.receive(true);
                System.out.println("Receive!!!!");
                String ipServer = srh.getConnectionSocket().getInetAddress().getHostAddress();
                //System.out.println("ip server: "+ipServer);
                
                statusMachine = (StatusMachine) marshaller.unmarshall(msg);
                ProcessStatusMachine processStatusMachine = new ProcessStatusMachine(statusMachine, limitResourcesMachine,machines,ipServer);
                processStatusMachine.start();
                 // tem que ter o ip da máquina que vai pausar, o ip da máquina que vai iniciar para fazer o rebind no 
                 // servidor de nomes, tem que ter o nome das duas máquinas também, para o devstack pausar uma e despausar
                 // a outra (Show details of instance: openstack server show NAME)
                 
            }   
            
            
        } catch (Exception e) {
        }
        
        
    }
    
}
