/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.naming;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import middleware.client.ClientProxy;
import middleware.client.ClientRequestHandler;
import middleware.util.Marshaller;
import middleware.util.Message;
import middleware.util.MessageBody;
import middleware.util.MessageHeader;
import middleware.util.ReplyBody;
import middleware.util.ReplyHeader;
import middleware.util.RequestBody;
import middleware.util.RequestHeader;
import monitor.GetInfo;

/**
 *
 * @author gprt
 */
public class NamingProxy implements INaming {
    private NamingRepository namingRepository;
    private String host;
    private int port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public NamingProxy(String host,int port) {
        
        this.host = host;
        this.port = port;
        
    }
    
    public NamingProxy(int port){
        //info.readInfo("info.cfg");
            
        this.host = GetInfo.getInstance().getServerNameIP(); //"10.0.2.15"; // AQUI
        this.port = port;
        
    }

    @Override
    public ClientProxy lookup(String serviceName) {
        ArrayList<Object> parameters = new ArrayList<Object>();
        parameters.add(serviceName);
        Message message=new Message();
        message.setMessageBody(new MessageBody(
                new RequestHeader("", 0, true, 0, "lookup"),
                new RequestBody(parameters),
                new ReplyHeader(),
                new ReplyBody(null)));
        message.setMessageHeader(
                new MessageHeader("MIOP", 0, true, 0, 0));
        ClientRequestHandler crh=new ClientRequestHandler(this.host, this.port);
        try {
            Marshaller marshaller = new Marshaller();
            crh.send(marshaller.marshall(message));
            byte[] messageMarshalled=crh.receive();
            Message messageUnmarshalled=(Message) marshaller.unmarshall(messageMarshalled);
            
            return (ClientProxy) messageUnmarshalled.getMessageBody().getReplyBody().getOperationResult();
        } catch (IOException ex) {
            Logger.getLogger(NamingProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(NamingProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<String> list() {
        ArrayList<String> serviceNames = new ArrayList<String>();
        return serviceNames;
    }

    @Override
    public void bind(String serviceName, ClientProxy clientProxy) {
        ArrayList<Object> parameters = new ArrayList<Object>();
        parameters.add(serviceName);
        parameters.add(clientProxy);
         
        Message message=new Message();
        message.setMessageBody(new MessageBody(
                new RequestHeader("", 0, true, 0, "bind"),
                new RequestBody(parameters),
                null,
                null));
        message.setMessageHeader(
                new MessageHeader("MIOP", 0, true, 0, 0));
        ClientRequestHandler crh=new ClientRequestHandler(this.host, this.port);
        try {
            Marshaller marshaller = new Marshaller();
            crh.send(marshaller.marshall(message));
            crh.receive();
        } catch (IOException ex) {
            Logger.getLogger(NamingProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(NamingProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void unbind(String serviceName, ClientProxy clientProxy) {
        ArrayList<Object> parameters = new ArrayList<Object>();
        parameters.add(serviceName);
        parameters.add(clientProxy);
         
        Message message=new Message();
        message.setMessageBody(new MessageBody(
                new RequestHeader("", 0, true, 0, "unbind"),
                new RequestBody(parameters),
                null,
                null));
        message.setMessageHeader(
                new MessageHeader("MIOP", 0, true, 0, 0));
        ClientRequestHandler crh=new ClientRequestHandler(this.host, this.port);
        try {
            Marshaller marshaller = new Marshaller();
            crh.send(marshaller.marshall(message));
            crh.receive();
        } catch (IOException ex) {
            Logger.getLogger(NamingProxy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(NamingProxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
