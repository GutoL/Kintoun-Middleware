/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.naming;

import java.io.IOException;
import middleware.client.ClientProxy;
import middleware.server.ServerRequestHandler;
import middleware.util.Marshaller;
import middleware.util.Message;
import middleware.util.MessageBody;
import middleware.util.MessageHeader;
import middleware.util.ReplyBody;
import middleware.util.ReplyHeader;
import middleware.util.Termination;

/**
 * Invoke the methods implemented by NamingImplementation
 * Methods: bind, lookup, unbind, reactivate
 * @author Demis
 */
public class NamingInvoker {
    
    /**
     * Invoke a method implemented by Naming
     * Methods: bind, lookup, unbind, reactivate
     * bind: register a service
     * lookup: seek for a service
     * unbind: unbind a service
     * reactivate: deactivate a service
     * 
     * @param namingProxy
     * @throws IOException
     * @throws InterruptedException 
     */
    public void invoke(NamingProxy namingProxy) throws IOException, InterruptedException{
        ServerRequestHandler srh = new ServerRequestHandler(namingProxy.getPort());
        
        byte[] msgToBeUnmarshalled = null;
        byte[] msgMarshalled = null;
        Message msgUnmarshalled = new Message();
        
        NamingImplementation namingImplementation= new NamingImplementation();
        Marshaller marshaller= new Marshaller();
        Termination ter = new Termination();
        ClientProxy clientProxy=null;
        
        while (true){
           
            msgToBeUnmarshalled = srh.receive();
            msgUnmarshalled = (Message) marshaller.unmarshall(msgToBeUnmarshalled);
            String serviceName= (String) msgUnmarshalled.getMessageBody().getRequestBody().getParameters().get(0);
            
            if(msgUnmarshalled.getMessageBody().getRequestBody().getParameters().size()>1){
                clientProxy= (ClientProxy) msgUnmarshalled.getMessageBody().getRequestBody().getParameters().get(1);
                
            }
                    
            
            switch(msgUnmarshalled.getMessageBody().getRequestHeader().getOperation()){
                case "lookup":
                    ClientProxy serviceRequested=namingImplementation.lookup(serviceName);
                    
                    Message responseMessage= new Message(
                        new MessageHeader("MIOP", 0, false, 0, 0),
                        new MessageBody(null, null, new ReplyHeader("",0,0), new ReplyBody(serviceRequested)));
                    msgMarshalled=marshaller.marshall(responseMessage);
                    srh.send(msgMarshalled);
                    
                    break;
                    
                case "bind":
                    
                    namingImplementation.bind(serviceName,clientProxy);
                    
                    Message responseMessage2= new Message(
                        new MessageHeader("MIOP", 0, false, 0, 2),
                        new MessageBody(null, null, new ReplyHeader("",0,0), new ReplyBody("OK")));
                    msgMarshalled=marshaller.marshall(responseMessage2);
                    
                    srh.send(msgMarshalled);
                    
                    break;                               
                    
                case "unbind":
                    
                    namingImplementation.unbind(serviceName,clientProxy);
                    
                    Message responseMessageUnbind= new Message(
                        new MessageHeader("MIOP", 0, false, 0, 2),
                        new MessageBody(null, null, new ReplyHeader("",0,0), new ReplyBody("OK")));
                    msgMarshalled=marshaller.marshall(responseMessageUnbind);
                    
                    srh.send(msgMarshalled);
                    
                    break;
                    
                case "reactivate":
                    namingImplementation.reactivate(serviceName, clientProxy);
                    
                    Message responseMessageReactivate= new Message(
                        new MessageHeader("MIOP", 0, false, 0, 0),
                        new MessageBody(null, null, new ReplyHeader("",0,0), new ReplyBody("OK")));
                    msgMarshalled=marshaller.marshall(responseMessageReactivate);
                    srh.send(msgMarshalled);
                    
                    break;
                    
                
            }  
            
        }
    }
}
