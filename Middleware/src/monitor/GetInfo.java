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
 *
 * @author guto
 */
public class GetInfo {
    
    private static GetInfo instance = null;
    String tenant, user, password, serverNameIP, networkName;

    
    
    public static synchronized GetInfo getInstance(){
        if(instance == null){
            instance = new GetInfo();
            instance.readInfo("info.cfg");
        }
        
        return instance;
        
    }

    public String getServerNameIP() {
        return serverNameIP;
    }


    public String getPassword() {
        return password;
    }

    public String getTenant() {
        return tenant;
    }

    public String getUser() {
        return user;
    }

    public String getNetworkName() {
        return networkName;
    }
    
    
    
    public void readInfo(String path){
        ArrayList<String> lines=new ArrayList<>();
        
         try {
            FileReader arq = new FileReader(path);
            BufferedReader lerArq = new BufferedReader(arq);

            String line = lerArq.readLine(); // lê a primeira linha
      
            while (line != null) {
              //System.out.printf("%s\n", line);
              
              lines.add(line); // lê da segunda até a última linha
              line = lerArq.readLine();
              
            }

            arq.close();
            this.tenant = lines.get(0);
            this.user = lines.get(1);
            this.password = lines.get(2);
            this.serverNameIP = lines.get(3);
            this.networkName = lines.get(4);
            
          } catch (IOException e) {
              System.err.printf("Erro na abertura do arquivo: %s.\n",
                e.getMessage());
          }
        
        
    }
}
