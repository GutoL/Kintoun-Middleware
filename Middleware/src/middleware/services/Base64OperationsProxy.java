/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.services;

import java.io.Serializable;
import java.util.ArrayList;
import middleware.client.ClientProxy;
import middleware.client.Requestor;
import middleware.naming.NamingProxy;
import middleware.util.Invocation;
import middleware.util.Termination;

/**
 *
 * @author Demis e Lucas
 */
public class Base64OperationsProxy extends ClientProxy implements IBase64Operations, Serializable{

    public Base64OperationsProxy(String host, int port){
        super(host,port);
    }

    public Base64OperationsProxy(){
        
    }
    @Override
    public String encode(String s) throws Throwable {
        Invocation invocation=new Invocation();
        Termination termination = new Termination();
        ArrayList<Object> parameters = new ArrayList<Object>();
        class Local {};
        String methodName=null;
        Requestor requestor=new Requestor();
        
        methodName = Local.class.getEnclosingMethod().getName();
        parameters.add(s);
        
        invocation.setObjectId(this.getObjectId());
         invocation.setOperationName(methodName);
        invocation.setParameters(parameters);
        
        invocation.setIpAddress(this.getHost());
        invocation.setPortNumber(this.getPort());
    
        for (int x = 0; x < 3; x++) {
        
            try {
                termination=requestor.invoke(invocation);
                return (String) termination.getResult(); 
            } catch (Exception e) {
                
                NamingProxy namingProxy = new NamingProxy("localhost",2017);
                Base64OperationsProxy b64proxy = (Base64OperationsProxy)namingProxy.lookup("Base64");
                invocation.setIpAddress(b64proxy.getHost());
                invocation.setPortNumber(b64proxy.getPort());
            
            }
            
            
        }
        
        return null;
    }

    @Override
    public String decode(String s) throws Throwable {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
    
}
