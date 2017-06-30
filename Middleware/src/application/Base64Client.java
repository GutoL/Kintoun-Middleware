/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.util.Random;
import middleware.naming.NamingProxy;
import middleware.services.Base64OperationsProxy;

/**
 * Example of client using middleware.
 * @author gprt
 */
public class Base64Client {
    
    public static void main(String[]args) throws Throwable{
        
        NamingProxy namingProxy;
         
        Base64OperationsProxy b64proxy;
        
        
        Random gerador = new Random();
        
       
            while(true){
                
                namingProxy = new NamingProxy(2017);
                b64proxy = (Base64OperationsProxy)namingProxy.lookup("Base64");
               
                 if (b64proxy!=null){
                    
                  
                    System.out.println("Enviado requisição para o IP: "+b64proxy.getHost());
                    String result=b64proxy.encode(String.valueOf(gerador.nextDouble()));
                    System.out.println("Resultado: "+result);

                    Thread.sleep(3000);
                 }
                 else{
                     System.out.println("application.Base64Client.main() Lookup não retornou um serviço válido. Tente novamente após alguns segundos");
                    }
            
            }
            
       }
        
        
}

