/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.IOException;
import middleware.naming.NamingInvoker;
import middleware.naming.NamingProxy;

/**
 *
 * @author guto
 */
public class ServerNames {
    
    public static void main(String args[]) throws IOException, InterruptedException{
        
        System.out.println("Registry is ready...");
        
 
        NamingProxy namingProxy=new NamingProxy("localhost",2017);
        NamingInvoker invoker = new NamingInvoker();
        
        System.out.println(namingProxy.getHost());
        
        invoker.invoke(namingProxy);
        
    }
    
}
