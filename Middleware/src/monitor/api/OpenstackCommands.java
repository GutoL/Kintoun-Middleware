/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import monitor.GetInfo;
import monitor.MachineInformation;
import org.json.JSONArray;
import org.json.JSONException;


/**
 *
 * @author gprt
 */
public class OpenstackCommands {
    public static String token;
    
    private static String NETWORK;
    
    public ArrayList<MachineInformation> getServers(){
        Requests requests=new Requests();
        ArrayList<MachineInformation> serversInformation=new ArrayList<>();
        HttpResponse<JsonNode> serversRequest=requests.requestServers(token);
        
        NETWORK = GetInfo.getInstance().getNetworkName();
        
         
        try {
            JSONArray jsonArray= serversRequest.getBody().getObject().getJSONArray("servers");
            
            for(int i=0;i<jsonArray.length();i++){
                String id=jsonArray.getJSONObject(i).getString("id");
                serversInformation.add(getServer(id));
            }    
            
        } catch (JSONException ex) {
            Logger.getLogger(OpenstackCommands.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (MachineInformation mi: serversInformation){
            System.out.println("monitor.api.OpenstackCommands.getServers() "+mi.getIP() + " "+mi.getId());
        }
        return serversInformation;
    }
    
    public MachineInformation getServer(String id){
        
        Requests requests=new Requests();
        HttpResponse<JsonNode> serversRequest=requests.requestServer(token,id);
        MachineInformation machine=new MachineInformation();
        
        NETWORK = GetInfo.getInstance().getNetworkName();
        
        
        try {
            
            JSONArray networks=serversRequest.getBody().getObject().getJSONObject("server").getJSONObject("addresses").getJSONArray(NETWORK);
            int index=0;
            
                
            for(int i=0;i<networks.length();i++){
                
                if(networks.getJSONObject(i).getString("OS-EXT-IPS:type").equals("floating")){
                     index=i;
                }        
                
            }
            String ip=serversRequest.getBody().getObject().getJSONObject("server").getJSONObject("addresses").getJSONArray(NETWORK).getJSONObject(index).getString("addr");
            String name=serversRequest.getBody().getObject().getJSONObject("server").getString("name");
            machine.setIP(ip);
            machine.setId(id);
            machine.setName(name);
            machine.setPort(1010);
             
        } catch (JSONException ex) {
            Logger.getLogger(OpenstackCommands.class.getName()).log(Level.SEVERE, null, ex);
            machine=null;
        }
        
        //machine.setIP();
        return machine;
       
    }
    
    
    public void pauseInstance(String id){
        
        Requests requests=new Requests();
        HttpResponse<JsonNode> serversRequest = requests.requestOP(token, id, "pause");
        /*try {
            
            System.out.println(serversRequest.getBody());
            
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        
        
    }
    
    public void unPauseInstance(String id){
     
        Requests requests=new Requests();
        HttpResponse<JsonNode> serversRequest = requests.requestOP(token, id, "unpause");
        /*try {
            
            System.out.println(serversRequest.getBody());
            
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        
    }
    
    
}