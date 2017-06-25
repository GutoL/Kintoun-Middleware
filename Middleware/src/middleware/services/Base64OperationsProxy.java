/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.services;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import middleware.client.ClientProxy;
import middleware.client.Requestor;
import middleware.naming.NamingProxy;
import middleware.util.Invocation;
import middleware.util.Termination;
import monitor.GetInfo;

/**
 *
 * @author Demis e Lucas
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
        
        
        try {
            
            //String host2 = InetAddress.getLocalHost().getHostAddress();// get local IP
            this.host = host2;
            //this.host = NetworkInterface.get;
            this.port = port;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
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
                System.out.println("se liga");
                termination=requestor.invoke(invocation);
                return (String) termination.getResult(); 
            } catch (Exception e) {
                
                this.namingServerIP = GetInfo.getInstance().getServerNameIP();
                
                NamingProxy namingProxy = new NamingProxy(this.namingServerIP,2017);
                
                //System.out.println("antes do lookup =]");
                Base64OperationsProxy b64proxy = (Base64OperationsProxy)namingProxy.lookup("Base64");
                //System.out.println(""+b64proxy.getHost()+" "+b64proxy.getPort());
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
