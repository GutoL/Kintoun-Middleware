/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor.api;

import monitor.GetInfo;

/**
 * Class that build URL OpenStack to requisitions.
 * @author gprt
 */
public class URL {
    
    private final String serverIP = GetInfo.getInstance().getOpenStackIP();
            
    public String getServerIP() {
        return serverIP;
    }
    
    public String getIdentityTokens(){
        return "http://"+serverIP+"/identity/v2.0/tokens";
    }
    
    public String getServers(){
        return "http://"+serverIP+":8774/v2.1/servers";

    }
    
    public String getServers(String id){
        return "http://"+serverIP+":8774/v2.1/servers/"+id;

    }
    
    public String basicURL(){
        return "http://"+serverIP+":8774/v2.1/";
    }
    
}
