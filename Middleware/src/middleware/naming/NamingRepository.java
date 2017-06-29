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
 *
 * @author gprt
 */
public class NamingRepository {
    public static HashMap<String,ArrayList<NamingRecord>> records = new HashMap<String,ArrayList<NamingRecord>>();
    
    public HashMap<String,ArrayList<NamingRecord>> getRecords() {
        return records;
    }

    public void setRecords(HashMap<String,ArrayList<NamingRecord>> records) {
        this.records = records;
    }
    
   
    
    public void addRecord(String serviceName, ClientProxy clientProxy){
        NamingRecord namingRecord=new NamingRecord(serviceName, clientProxy);
        if (this.getRecord(serviceName)!=null){
            //se ja contem o servico avisa que ja ta inserido
            if(this.getRecords().get(serviceName).contains(clientProxy)){
            
                 System.out.println("middleware.naming.NamingRepository.addRecord() clientProxy already added");
            }
            else{
                this.getRecords().get(serviceName).add(namingRecord);
            }
        }
        //se o servico nao existe, cria-o
        else{
            this.getRecords().put(serviceName, new ArrayList<NamingRecord>());
            this.getRecords().get(serviceName).add(namingRecord);
        }
        
        for (NamingRecord records : this.getRecords().get(serviceName)){
            System.out.println("middleware.naming.NamingRepository.addRecord() "+records.getServiceName()+" "+records.getClientProxy());
        
        }
        
    }
    
    public ClientProxy getRecord(String serviceName){
        ClientProxy result=null;
        boolean allPaused=true;
        ArrayList<NamingRecord> namingRecords=this.getRecords().get(serviceName);
        if (namingRecords!=null  && !namingRecords.isEmpty()){
            Random random=new Random();
            //System.out.println("middleware.naming.NamingRepository.getRecord() "+namingRecords.size());
            for(NamingRecord nr: namingRecords){
                //System.out.println("middleware.naming.NamingRepository.getRecord() "+nr.getClientProxy().getHost() +nr.getClientProxy().isPaused());
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
     * @param serviceName
     * @param clientProxy 
     */
    
    public void disableRecord(ClientProxy clientProxy){
        setPausedProxy(clientProxy, true);
    }
    
    
    public void reactivateRecord(ClientProxy clientProxy){
        setPausedProxy(clientProxy, false);
    }
    
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