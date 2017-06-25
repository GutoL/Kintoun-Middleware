/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.util.Random;
import middleware.naming.NamingProxy;
//import middleware.naming.NamingServer;
import middleware.services.Base64OperationsProxy;

/**
 *
 * @author gprt
 */
public class Base64Client {
    
    public static void main(String[]args) throws Throwable{
        /*String namingServerIP="localhost";
        
        try{
            namingServerIP=args[0];
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Base64Server.main() args not found, using localhost");
        }*/
        
        //NamingProxy namingProxy = new NamingProxy(args[0],2017);// servidor de nomes
        NamingProxy namingProxy = new NamingProxy(2017);// servidor de nomes
         
        Base64OperationsProxy b64proxy = (Base64OperationsProxy)namingProxy.lookup("Base64");
        //b64proxy.setNamingServerIP(namingServerIP);
        
        Random gerador = new Random();
        
        if (b64proxy!=null){
            System.out.println("middleware.application.Base64Client.main() "+b64proxy);
            System.out.println("middleware.application.Base64Client.main() "+b64proxy.getHost()+" "+b64proxy.getPort());
            
            while(true){
                
                String result=b64proxy.encode(String.valueOf(gerador.nextDouble()));
                System.out.println(result); 
            
            }
            
        }
        
        else{
            System.out.println("application.Base64Client.main() Lookup nao retornou um servico valido. Tente novamente apos alguns segundos");
        }
    }
}
