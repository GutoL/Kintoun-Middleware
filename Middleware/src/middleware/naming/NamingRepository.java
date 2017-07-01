/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.naming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import middleware.client.ClientProxy;

/**
 * Register/Unregister a NamingRecord of a remote object
 * 
 * @author Demis
 */
public class NamingRepository {
    public static HashMap<String,ArrayList<NamingRecord>> records = new HashMap<String,ArrayList<NamingRecord>>();
    
    public HashMap<String,ArrayList<NamingRecord>> getRecords() {
        return records;
    }

    public void setRecords(HashMap<String,ArrayList<NamingRecord>> records) {
        this.records = records;
    }
    
   /**
    * Adds a record in NamingRepository
    * @param serviceName the name of service to be added
    * @param clientProxy the location of object
    */
    
    public void addRecord(String serviceName, ClientProxy clientProxy){
        NamingRecord namingRecord=new NamingRecord(serviceName, clientProxy);
        if (this.getRecord(serviceName)!=null){
            
            if(!this.getRecords().get(serviceName).contains(namingRecord)){
            
                this.getRecords().get(serviceName).add(namingRecord);
            }
        }
        //If the service does not exist, creates it
        else{
            this.getRecords().put(serviceName, new ArrayList<NamingRecord>());
            this.getRecords().get(serviceName).add(namingRecord);
        }
               
    }
    /**
     * get the record of a serviceName related to a remote object
     * returns only if the variable paused of the ClientProxy is false
     * if two or more clientProxies are able to be choose, returns randomly one of them
     * @param serviceName the name of service 
     * @return clientProxy that offers the service
     */
    public ClientProxy getRecord(String serviceName){
        ClientProxy result=null;
        boolean allPaused=true;
        ArrayList<NamingRecord> namingRecords=this.getRecords().get(serviceName);
        if (namingRecords!=null  && !namingRecords.isEmpty()){
            Random random=new Random();
            for(NamingRecord nr: namingRecords){
                if(!nr.getClientProxy().isPaused()){
                    allPaused=false;
                }
            }
            
            boolean paused=true;
            while(paused && !allPaused){
                int index=random.nextInt(namingRecords.size());
                paused=namingRecords.get(index).getClientProxy().isPaused();
                result=namingRecords.get(index).getClientProxy();
            }
            
        }   
        return result;
    }
    
    /**
     * Disables the first IP occurence of Client Proxy of each service
     * put paused=true
     * @param clientProxy The client proxy to be deactivated
     */
    
    public void disableRecord(ClientProxy clientProxy){
        setPausedProxy(clientProxy, true);
    }
    
    /**
     * reactivates a clientProxy, put paused=false
     * @param clientProxy The client proxy to be reactivate 
     */
    
    public void reactivateRecord(ClientProxy clientProxy){
        setPausedProxy(clientProxy, false);
    }
    
    /**
     * Changes the paused variable of a Client proxy to false or true
     * 
     * @param clientProxy client proxy to be changed
     * @param bool value of paused (true or false)
     * @return changed (if clientProxy was changed or not)
     */
    
    private boolean setPausedProxy(ClientProxy clientProxy, boolean bool){
        int index=0;
        boolean changed=false;
        for (ArrayList<NamingRecord> namingRecords : this.getRecords().values()){
            if (!namingRecords.isEmpty()){
                for (int i=0;i<namingRecords.size();i++){
                    if (namingRecords.get(i).getClientProxy().getHost().equals(clientProxy.getHost())){
                        index=i;
                        namingRecords.get(i).getClientProxy().setPaused(bool);
                        System.out.println("middleware.naming.NamingRepository.SetPausedProxy() "+namingRecords.get(i).getClientProxy().getHost()+" " +namingRecords.get(i).getClientProxy().isPaused());
                        changed=true;
                        System.out.println("middleware.naming.NamingRepository.SetPausedProxy() changed");
                        
                        break;
                    }
                }
                
            }

        }
        return changed;
    }
    
}