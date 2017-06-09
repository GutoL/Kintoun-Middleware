/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
        String myServerIP="localhost";
        
        try{
            namingServerIP=args[0];
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Base64Server.main() args[0] not found, using localhost as namingServer");
        }
        
        try{
            myServerIP=args[1];
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Base64Server.main() args[1] not found, using localhost as myServer");
        }
        
        Base64Invoker invoker = new Base64Invoker();
        System.out.println("Server running");
        Base64OperationsProxy base64 = new Base64OperationsProxy(myServerIP,2018);
        System.out.println("Server running");
        //NamingProxy namingProxy = new NamingProxy(args[0],2017);
        NamingProxy namingProxy = new NamingProxy(namingServerIP,2017);
        System.out.println("middleware.application.Base64Server.main() "+base64.getHost());
        namingProxy.bind("Base64", base64);
        System.out.println("middleware.application.Base64Server.main() "+namingProxy.list());
        
        invoker.invoke(base64);
    }   
}
