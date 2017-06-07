/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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
            
            // aqui dá o comando para o devstack para a máquina, iniciar a standby e fazer o rebind no servidor de nomes
            System.out.println("Stop machine: "+statusMachine.nameMachine);
            //execScriptSH("src/monitor/Shell/pause.sh", statusMachine.nameMachine);// pause machine
            execScriptWithReturn("src/monitor/Shell/pause.sh", statusMachine.nameMachine);// pause machine with return
        }
        
    }
    
    public void execScriptSH(String script_name,String machine){
        
        try {
        
            System.out.println("EXEC file: ./"+script_name+" "+machine);
            Runtime run = Runtime.getRuntime();
            //run.exec("./CommunicationDevstack/PauseInstance.sh "+machine);
            run.exec("./"+script_name+" "+machine);
            Thread.sleep(300);
            /*ProcessBuilder pb = new ProcessBuilder("teste.sh");
            Process p = pb.start();
            */
            
            /*String command = "";

            Process proc = Runtime.getRuntime().exec(command);

            // Read the output

            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            String line = "";
            while((line = reader.readLine()) != null) {
                System.out.print(line + "\n");
            }

            proc.waitFor();*/
            
            
        } catch (Exception e) {
            System.out.println("Error");
             
        }
        
        
    }
    
    
    public void execScriptWithReturn(String script_name,String machine){
    
        try {
            System.out.println("Running: "+"./"+script_name+" "+machine);
            
            Runtime rt = Runtime.getRuntime();
            //String[] commands = {"system.exe","-get t"};
            String commands = "./"+script_name+" "+machine;
            //commands = "ifconfig";
            Process proc = rt.exec(commands);

            BufferedReader stdInput = new BufferedReader(new 
                 InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new 
                 InputStreamReader(proc.getErrorStream()));

            // read the output from the command
            //System.out.println("Here is the standard output of the command:\n");
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            // read any errors from the attempted command
            //System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }


            } catch (Exception e) {
                System.out.println("Error: "+"./"+script_name+" "+machine);
            }
    }

    
    
}
