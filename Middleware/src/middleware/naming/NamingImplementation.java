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
public class NamingImplementation implements INaming{

    @Override
    public void bind(String serviceName, ClientProxy clientProxy) {
        NamingRepository namingRepository=new NamingRepository();
        namingRepository.addRecord(serviceName, clientProxy);
        
        System.out.println("middleware.naming.NamingImplementation.bind() "+clientProxy.getHost());
    }

    @Override
    public ClientProxy lookup(String serviceName) {
        NamingRepository namingRepository=new NamingRepository();
        return namingRepository.getRecord(serviceName);
    }

    @Override
    public ArrayList<String> list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void unbind(String serviceName, ClientProxy clientProxy) {
        NamingRepository namingRepository=new NamingRepository();
        namingRepository.deleteRecord(serviceName,clientProxy);
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
