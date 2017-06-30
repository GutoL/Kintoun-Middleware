/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * class that contains informations passed to middleware
 * @author guto
 */
public class GetInfo {
    
    private static GetInfo instance = null;
    String tenant, user, password, serverNameIP, gptIP, networkName, OpenStackIP;
    float limitResourcesMachine;

    /**
     * Singleton project pattern.When invoked, the informations in configuration file are load in this class.
     * @return 
     */
    public static synchronized GetInfo getInstance(){
        if(instance == null){
            instance = new GetInfo();
            instance.readInfo("info.cfg");
        }
        
        return instance;
        
    }

    /**
     * Return Server names IP.
     * @return 
     */
    public String getServerNameIP() {
        return serverNameIP;
    }

    /**
     * Return password used in OpenStack.
     * @return 
     */
    public String getPassword() {
        return password;
    }

    /**
     * Return tanant name used in OpenStack evironment.
     * @return 
     */
    public String getTenant() {
        return tenant;
    }

    /**
     * Return user names of OpenStack.
     * @return 
     */
    public String getUser() {
        return user;
    }

    /**
     * Return network name where instances are connected.
     * @return 
     */
    public String getNetworkName() {
        return networkName;
    }

    /**
     * Return IP of instances manager.
     * @return 
     */
    public String getGptIP() {
        return gptIP;
    }

    /**
     * Return resource limit of instances (e.g. CPU consumption).
     * @return 
     */
    public float getLimitResourcesMachine() {
        return limitResourcesMachine;
    }

   /**
    * Return IP of machine where OpenStack was installed.
    * @return 
    */
    public String getOpenStackIP() {
        return OpenStackIP;
    }
    
    
    /**
     * Method that read configuration file.
     * @param path 
     */
    public void readInfo(String path){
        ArrayList<String> lines=new ArrayList<>();
        
         try {
            FileReader arq = new FileReader(path);
            BufferedReader lerArq = new BufferedReader(arq);

            String line = lerArq.readLine(); // lê a primeira linha
            
            int i = 1;
            while (line != null) {
              
              if(i % 2 == 0){
                lines.add(line); 
              }
              
              line = lerArq.readLine();// lê da segunda até a última linha
              i++;
              
            }
            
            lerArq.close();
            arq.close();
            this.tenant = lines.get(0);
            this.user = lines.get(1);
            this.password = lines.get(2);
            this.networkName = lines.get(3);
            this.OpenStackIP = lines.get(4);
            this.serverNameIP = lines.get(5);
            this.gptIP = lines.get(6);
            this.limitResourcesMachine = Float.parseFloat(lines.get(7));
            
            
          } catch (IOException e) {
              System.err.printf("Error in open file: %s.\n",
                e.getMessage());
          }
        
        
    }
}
