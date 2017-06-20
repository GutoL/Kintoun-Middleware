/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.IOException;
import java.rmi.RemoteException;
import middleware.naming.NamingProxy;
import middleware.server.Base64Invoker;
import middleware.services.Base64OperationsProxy;

/**
 *
 * @author gprt
 */
public class Base64Server {
    
    public static void main(String[]args) throws IOException, RemoteException, InterruptedException{
        String namingServerIP="localhost";
        String GPTIP = "localhost";
        //String myServerIP="localhost";
        
        try{
            namingServerIP=args[0];
            GPTIP = args[1];
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Base64Server.main() args[0] not found, using localhost as namingServer");
        }
        
        
        Base64Invoker invoker = new Base64Invoker();
        invoker.setGPTIP(GPTIP);
        System.out.println("Server running");
        Base64OperationsProxy base64 = new Base64OperationsProxy(2018);
        System.out.println("Server running");
        
        //NamingProxy namingProxy = new NamingProxy(args[0],2017);
        NamingProxy namingProxy = new NamingProxy(namingServerIP,2017);
        System.out.println("middleware.application.Base64Server.main() "+base64.getHost());
        namingProxy.bind("Base64", base64);
        System.out.println("middleware.application.Base64Server.main() "+namingProxy.list());
        
        invoker.invoke(base64);
    }   
}
