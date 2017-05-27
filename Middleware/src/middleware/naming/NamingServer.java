/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.naming;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author gprt
 */
public class NamingServer {
    public static void main(String[]args) throws RemoteException, IOException, InterruptedException{
      
        System.out.println("Registry is ready...");
 
        NamingProxy namingProxy=new NamingProxy("localhost",2017);
        NamingInvoker invoker = new NamingInvoker();
        
        invoker.invoke(namingProxy);
    }
}
