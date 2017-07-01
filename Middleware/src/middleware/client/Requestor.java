/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.client;

import java.io.IOException;
import java.net.UnknownHostException;
import middleware.util.Invocation;
import middleware.util.Marshaller;
import middleware.util.Message;
import middleware.util.MessageBody;
import middleware.util.MessageHeader;
import middleware.util.RequestBody;
import middleware.util.RequestHeader;
import middleware.util.Termination;

/**
 * Requestor
 * Prepare the message and send to host in order to invoke the remote object 
 * Try request the object 5 fives if do not receive a response in a defined time
 * @author Demis
 */
public class Requestor {
    /**
     * invoke a remote object
     * if do not have a response, try five times and request a new ClientProxy to Naming Server
     * @param invocation
     * @return
     * @throws UnknownHostException
     * @throws IOException
     * @throws Throwable 
     */
    public Termination invoke(Invocation invocation) throws UnknownHostException, IOException, Throwable{
        
        
        Marshaller marshaller = new Marshaller();
        Termination termination = new Termination();
        
        byte[] msgMarshalled;
        byte[] msgToBeUnmarshalled;
        Message msgUnmarshalled=new Message();
        Message msgToBeMarshalled=new Message();
        
       
        //creates the request and encapsulates it in a message
        RequestHeader requestHeader= new RequestHeader("",0,true,0,invocation.getOperationName());
        RequestBody requestBody = new RequestBody(invocation.getParameters());
        MessageHeader messageHeader = new MessageHeader("MIOP",0,false,0,0);
        MessageBody messageBody = new MessageBody(requestHeader,requestBody,null,null);
        //recover the message to be sent to bottom layer
        msgToBeMarshalled = new Message(messageHeader, messageBody);
        
       //make the marshall
       msgMarshalled = marshaller.marshall(msgToBeMarshalled);
       
       int limit = 5;
       int time = 50;
       int attemptsSendMessage;
       
        ClientRequestHandler crh=new ClientRequestHandler(invocation.getIpAddress(), invocation.getPortNumber());
        
        
        for(attemptsSendMessage=0; attemptsSendMessage < limit; attemptsSendMessage++) {            

            try {

                //send request message
                crh.send(msgMarshalled);
                
                //receive the response message
                msgToBeUnmarshalled = crh.receive();
                
                //unmarshall message
                msgUnmarshalled = (Message)marshaller.unmarshall(msgToBeUnmarshalled);

                termination.setResult(msgUnmarshalled.getMessageBody().getReplyBody().getOperationResult());

                return termination;


                } catch (Exception e) {

                    Thread.sleep(time);
                    time = time + 100;
                    System.out.println("Try again..."+attemptsSendMessage);
                   
                }

            }
       
        return null;
    }
    
}
