/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.client;

import java.io.IOException;
import java.net.UnknownHostException;
import middleware.naming.NamingProxy;
import middleware.services.Base64OperationsProxy;
import middleware.util.Invocation;
import middleware.util.Marshaller;
import middleware.util.Message;
import middleware.util.MessageBody;
import middleware.util.MessageHeader;
import middleware.util.RequestBody;
import middleware.util.RequestHeader;
import middleware.util.Termination;

/**
 *
 * @author Demis e Lucas
 */
public class Requestor {
    public Termination invoke(Invocation invocation) throws UnknownHostException, IOException, Throwable{
        
        
        Marshaller marshaller = new Marshaller();
        Termination termination = new Termination();
        
        byte[] msgMarshalled;
        byte[] msgToBeUnmarshalled;
        Message msgUnmarshalled=new Message();
        Message msgToBeMarshalled=new Message();
        
       
        //cria o request, encapsulando-o numa mensagem
        RequestHeader requestHeader= new RequestHeader("",0,true,0,invocation.getOperationName());
        RequestBody requestBody = new RequestBody(invocation.getParameters());
        MessageHeader messageHeader = new MessageHeader("MIOP",0,false,0,0);
        MessageBody messageBody = new MessageBody(requestHeader,requestBody,null,null);
        //recupera a mensagem a ser enviada para a camada inferior
        msgToBeMarshalled = new Message(messageHeader, messageBody);
        
       //faz o marshall da mensagem
       msgMarshalled = marshaller.marshall(msgToBeMarshalled);
       
       int limit = 5;
       int time = 50; // era 5000
       int attemptsGetIP , attemptsSendMessage;
       
        ClientRequestHandler crh=new ClientRequestHandler(invocation.getIpAddress(), invocation.getPortNumber());
        
        //for(attemptsGetIP=0; attemptsGetIP < limit; attemptsGetIP++){            
            
                for(attemptsSendMessage=0; attemptsSendMessage < limit; attemptsSendMessage++) {            

                   try {

                       //manda a mensagem de request
                       crh.send(msgMarshalled);
                       System.out.println("mandando para o servidor");

                       //recebe a mensagem de retorno
                       msgToBeUnmarshalled = crh.receive();
                       System.out.println("Recebi do servidor!");
                       //dá o unmarshall da mensagem
                       msgUnmarshalled = (Message)marshaller.unmarshall(msgToBeUnmarshalled);

                       termination.setResult(msgUnmarshalled.getMessageBody().getReplyBody().getOperationResult());

                       return termination;


                   } catch (Exception e) {

                       Thread.sleep(time);
                       time = time + 1000;
                       System.out.println("Try again..."+attemptsSendMessage);
                   }

               }
        //} 
       
       
       
        return null;
    }
    
}
