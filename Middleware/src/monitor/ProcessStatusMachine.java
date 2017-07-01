/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import middleware.client.ClientProxy;
import middleware.naming.NamingProxy;
import monitor.api.OpenstackCommands;

/**
 * Class that processing status of instances and decide whether to pause instance or not.
 * @author guto
 */
public class ProcessStatusMachine extends Thread{
    
    StatusMachine statusMachine;
    LimitResourcesMachine limitResourcesMachine;
    ArrayList<MachineInformation> machines = new ArrayList<>();
    String ipServer;
    OpenstackCommands openstack;

    public ProcessStatusMachine() {
    }

    public ProcessStatusMachine(StatusMachine statusMachine,LimitResourcesMachine limitResourcesMachine, 
            ArrayList<MachineInformation> machines, String ip) {
        this.statusMachine = statusMachine;
        this.limitResourcesMachine = limitResourcesMachine;
        this.machines = machines;
        this.ipServer = ip;
        openstack = new OpenstackCommands();
    }
    
       
    public void run(){
        
        //System.out.println("CPU "+statusMachine.CPUConsumption+" Limit: "+limitResourcesMachine.getLimitCPU());
        
        if(statusMachine.CPUConsumption >= this.limitResourcesMachine.getLimitCPU()){  
        
           NamingProxy namingProxy = new NamingProxy(GetInfo.getInstance().serverNameIP,2017);// servidor de nomes
           
            for (int i = 0; i < machines.size(); i++) {
                
                //System.out.println(machines.get(i).IP+" - "+this.ipServer);
                if(machines.get(i).IP.equals(this.ipServer)){ // procurando o IP da máquina que tem que pausar
                    
                    //execScriptWithReturn("src/monitor/Shell/pause.sh", machines.get(i).name);// pause machine with return
                    
                    
                    System.out.println("Pause machine: "+machines.get(i).name);
                    openstack.pauseInstance(machines.get(i).id);// pause machine
                    
                    ClientProxy clientToBeDeleted=new ClientProxy(ipServer,2018);
                    namingProxy.unbind("", clientToBeDeleted);
                    
                    //////////////////////////////////////////////////////////////////////////////////
                    GenerateRandom random = new GenerateRandom(i, machines.size());
                    // get random number different of i to unpause
                    int n = random.generate();
                    
                    System.out.println("Unpause machine: "+machines.get(n).name);
                    openstack.unPauseInstance(machines.get(n).id);// unpause another machine
                    ClientProxy clientToBeUnpaused=new ClientProxy(machines.get(n).getIP(),2018);
                    NamingProxy namingProxyReactivate = new NamingProxy(GetInfo.getInstance().serverNameIP,2017);// servidor de nomes
                    namingProxyReactivate.reactivate("",clientToBeUnpaused);
                    
                    //////////////////////////////////////////////////////////////////////////////////
                    
                    /*
                    if(i == (machines.size()-1)){
                        System.out.println("Unpause machine: "+machines.get(i-1).name);
                        openstack.unPauseInstance(machines.get(i-1).id);// unpause another machine
                        ClientProxy clientToBeUnpaused=new ClientProxy(machines.get(i-1).getIP(),2018);
                        NamingProxy namingProxyReactivate = new NamingProxy(GetInfo.getInstance().serverNameIP,2017);// servidor de nomes
                        namingProxyReactivate.reactivate("",clientToBeUnpaused);
                    
                    }
                    else{
                        System.out.println("Unpause machine: "+machines.get(i+1).name);
                        openstack.unPauseInstance(machines.get(i+1).id);// unpause another machine
                        ClientProxy clientToBeUnpaused=new ClientProxy(machines.get(i+1).getIP(),2018);
                        NamingProxy namingProxyReactivate = new NamingProxy(GetInfo.getInstance().serverNameIP,2017);// servidor de nomes
           
                        namingProxyReactivate.reactivate("",clientToBeUnpaused);
                    
                    }*/
                       
                    break;
                }
                
                
            }
           
        
        
        
        }
        
    }
    
    
    
    
    public void execScriptWithReturn(String script_name,String machine){
    
        try {
            System.out.println("Running: "+"./"+script_name+" "+machine);
            
            Runtime rt = Runtime.getRuntime();
            
            String commands = "./"+script_name+" "+machine;
            
            Process proc = rt.exec(commands);

            BufferedReader stdInput = new BufferedReader(new 
                 InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new 
                 InputStreamReader(proc.getErrorStream()));

            
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }


            } catch (Exception e) {
                System.out.println("Error: "+"./"+script_name+" "+machine);
            }
    }

    
    
}
