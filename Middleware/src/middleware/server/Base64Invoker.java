/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.server;

import java.io.IOException;
import java.rmi.RemoteException;
import middleware.client.ClientProxy;
import middleware.util.Message;
import middleware.services.*;
import middleware.util.Marshaller;
import middleware.util.MessageBody;
import middleware.util.MessageHeader;
import middleware.util.ReplyBody;
import middleware.util.ReplyHeader;
import middleware.util.Termination;
import monitor.MonitorAgent;
import monitor.ZabixFake;

/**
 * Invoke the Base64 object
 * @author Demis
 */
public class Base64Invoker {
    
    /**
     * invoke the methods implemented by Base64 object
     * has a Monitor Agent that collects the server info and sends to Monitor Server
     * methods: encode, decode
     * @param clientProxy
     * @throws RemoteException
     * @throws IOException
     * @throws InterruptedException 
     */
    public void invoke(ClientProxy clientProxy) throws RemoteException, IOException, InterruptedException{
        ServerRequestHandler srh = new ServerRequestHandler(clientProxy.getPort());
        byte[] msgToBeUnmarshalled = null;
        byte[] msgMarshalled = null;
        Message msgUnmarshalled = new Message();
        
        Base64Operations b64Obj= new Base64Operations();
        Marshaller marshaller= new Marshaller();
        Termination ter = new Termination();
        
        // starting monitor agent
        ZabixFake zf = new ZabixFake();
        MonitorAgent agent = new MonitorAgent(zf, 4); // time in seconds
        
        agent.start();
       
        while (true){
            msgToBeUnmarshalled = srh.receive();
            System.out.println("Received!!!!!!");
            msgUnmarshalled = (Message) marshaller.unmarshall(msgToBeUnmarshalled);
            String word= (String) msgUnmarshalled.getMessageBody().getRequestBody().getParameters().get(0);
                    
            
            switch(msgUnmarshalled.getMessageBody().getRequestHeader().getOperation()){
                case "encode":
                    ter.setResult(b64Obj.encode(word));
                    break;
                case "decode":
                    ter.setResult(b64Obj.decode(word));
                    break;                               
            }
            Message responseMessage= new Message(
                        new MessageHeader("MIOP", 0, false, 0, 0),
                        new MessageBody(null, null, new ReplyHeader("",0,0), new ReplyBody(ter.getResult())));
                msgMarshalled=marshaller.marshall(responseMessage);
                
                Thread.sleep(100);// AQUI
                
                srh.send(msgMarshalled);
            
            
        }
    }
    
}
