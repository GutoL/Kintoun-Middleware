/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.naming;

import java.util.ArrayList;
import middleware.client.ClientProxy;

/**
 *
 * @author gprt
 */
public class NamingRepository {
    public static ArrayList<NamingRecord> records = new ArrayList<NamingRecord>();
    
    public ArrayList<NamingRecord> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<NamingRecord> records) {
        this.records = records;
    }
    
   /* public static NamingRepository getInstance(){
        createInstanceIfNotExists();
        return instance;
    }
    */
    
    public void addRecord(String serviceName, ClientProxy clientProxy){
        this.getRecords().add(new NamingRecord(serviceName,clientProxy));
    }
    
    public ClientProxy getRecord(String serviceName){
        ClientProxy result=null;
        for (int i=0;i<this.getRecords().size();i++){
            if (this.getRecords().get(i).getServiceName().equals(serviceName)){
                result=this.getRecords().get(i).getClientProxy();
                break;
            }
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
