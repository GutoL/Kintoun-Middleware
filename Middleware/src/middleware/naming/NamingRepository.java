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
    
   /* public static NamingRepository getInstance(){
        createInstanceIfNotExists();
        return instance;
    }
    */
    
    public void addRecord(String serviceName, ClientProxy clientProxy){
        NamingRecord namingRecord=new NamingRecord(serviceName, clientProxy);
        if (this.getRecord(serviceName)!=null){
            //se ja contem o servico avisa que ja ta inserido
            if(this.getRecords().get(serviceName).contains(namingRecord)){
                System.out.println("middleware.naming.NamingRepository.addRecord() proxy already added");
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
        ArrayList<NamingRecord> namingRecords=this.getRecords().get(serviceName);
        if (namingRecords!=null){
            Random random=new Random();
            System.out.println("middleware.naming.NamingRepository.getRecord() "+namingRecords.size());
            int index=random.nextInt(namingRecords.size());
            result=namingRecords.get(index).getClientProxy();
        }
        return result;
    }
    
    /*private static void createInstanceIfNotExists(){
        if (instance == null){
            System.out.println("middleware.naming.NamingRepository.createInstanceIfNotExists()");
            instance=new NamingRepository();
    
        }
    }*/
}
