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
import monitor.GetInfo;

/**
 * Proxy for base64 operations
 * @author Demis
 */
public class Base64OperationsProxy extends ClientProxy implements IBase64Operations, Serializable{

    String namingServerIP;

    public void setNamingServerIP(String namingServerIP) {
        this.namingServerIP = namingServerIP;
    }

    public String getNamingServerIP() {
        return namingServerIP;
    }
    
    
    public Base64OperationsProxy(String host2, int port){
        
        this.host = host2;
        this.port = port;
            
    }

    public Base64OperationsProxy(){
        
    }
    /**
     * invokes the encode method of the Base64Operations
     * if do not receive a response after three tries, request a new clientProxy
     * @param s
     * @return
     * @throws Throwable 
     */
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
                
                this.namingServerIP = GetInfo.getInstance().getServerNameIP();
                
                NamingProxy namingProxy = new NamingProxy(this.namingServerIP,2017);
                
                
                Base64OperationsProxy b64proxy = (Base64OperationsProxy)namingProxy.lookup("Base64");
                if (b64proxy!=null){
                    invocation.setIpAddress(b64proxy.getHost());
                    invocation.setPortNumber(b64proxy.getPort());
            
                }
                
            }
            
            
        }
        
        return null;
    }

    @Override
    public String decode(String s) throws Throwable {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
    
}
