/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import middleware.naming.NamingProxy;
//import middleware.naming.NamingServer;
import middleware.services.Base64OperationsProxy;

/**
 *
 * @author gprt
 */
public class Base64Client {
    
    public static void main(String[]args) throws Throwable{
        NamingProxy namingProxy = new NamingProxy("localhost",2017);// servidor de nomes
        
        System.out.println("middleware.application.Base64Client.main() "+namingProxy.list());
        
        Base64OperationsProxy b64proxy = (Base64OperationsProxy)namingProxy.lookup("Base64");
        System.out.println("middleware.application.Base64Client.main() "+b64proxy);
        System.out.println("middleware.application.Base64Client.main() "+b64proxy.getHost()+" "+b64proxy.getPort());
        
        String result=b64proxy.encode("lala");
        
        System.out.println(result);
        
    }
}
