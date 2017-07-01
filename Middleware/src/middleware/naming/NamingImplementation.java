/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.naming;

import java.util.ArrayList;
import middleware.client.ClientProxy;

/**
 * Implementation of methods related to Naming Server
 * Implements INaming
 * These methods request NamingRepository to put/get a service
 * 
 * bind: add a NamingRecord of a remote object
 * lookup: get a NamingRecord of a remote object
 * list: do not implemented
 * unbind: deactivate a NamingRecord (put paused=true)
 * reactivate: unpause and activate a NamingRecord (put paused=false)
 * @author Demis
 */
public class NamingImplementation implements INaming{

    @Override
    public void bind(String serviceName, ClientProxy clientProxy) {
        NamingRepository namingRepository=new NamingRepository();
        namingRepository.addRecord(serviceName, clientProxy);
        
    }

    @Override
    public ClientProxy lookup(String serviceName) {
        NamingRepository namingRepository=new NamingRepository();
        return namingRepository.getRecord(serviceName);
    }

    @Override
    public ArrayList<String> list() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void unbind(String serviceName, ClientProxy clientProxy) {
        NamingRepository namingRepository=new NamingRepository();
        namingRepository.disableRecord(clientProxy);

    }
    @Override
    public void reactivate(String serviceName, ClientProxy clientProxy) {
        NamingRepository namingRepository=new NamingRepository();
        namingRepository.reactivateRecord(clientProxy);
        
    }
    
}