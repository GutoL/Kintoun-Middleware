/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Demis e Lucas
 */
public class ServerRequestHandler {

    public int getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    public ServerSocket getWelcomeSocket() {
        return welcomeSocket;
    }

    public void setWelcomeSocket(ServerSocket welcomeSocket) {
        this.welcomeSocket = welcomeSocket;
    }

    public Socket getConnectionSocket() {
        return connectionSocket;
    }

    public void setConnectionSocket(Socket connectionSocket) {
        this.connectionSocket = connectionSocket;
    }

    public int getSentMessageSize() {
        return sentMessageSize;
    }

    public void setSentMessageSize(int sentMessageSize) {
        this.sentMessageSize = sentMessageSize;
    }

    public int getReceivedMessageSize() {
        return receivedMessageSize;
    }

    public void setReceivedMessageSize(int receivedMessageSize) {
        this.receivedMessageSize = receivedMessageSize;
    }

    public DataOutputStream getOutToClient() {
        return outToClient;
    }

    public void setOutToClient(DataOutputStream outToClient) {
        this.outToClient = outToClient;
    }

    public DataInputStream getInFromClient() {
        return inFromClient;
    }

    public void setInFromClient(DataInputStream inFromClient) {
        this.inFromClient = inFromClient;
    }
    private int portNumber;
    private ServerSocket welcomeSocket;
    private Socket connectionSocket;
    
    private int sentMessageSize;
    private int receivedMessageSize;
    private DataOutputStream outToClient;
    private DataInputStream inFromClient;
    
    public ServerRequestHandler(int port){
        this.portNumber = port;
    }
 
    public byte[] receive() throws IOException{
        //verificar se é possível iniciar o servidor no construtor
        byte[] receivedMessage = null;
        welcomeSocket = new ServerSocket(portNumber);
        connectionSocket = welcomeSocket.accept();
        outToClient = new DataOutputStream(connectionSocket.getOutputStream());
        inFromClient = new DataInputStream(connectionSocket.getInputStream());
        
        receivedMessageSize = inFromClient.readInt();
        receivedMessage = new byte[receivedMessageSize];
        inFromClient.read(receivedMessage,0,receivedMessageSize);
        
        return receivedMessage;
    }
    
    public void send(byte[] msg) throws IOException{
        sentMessageSize = msg.length;
        outToClient.writeInt(sentMessageSize);
        outToClient.write(msg);
        outToClient.flush();
        
        connectionSocket.close();
        welcomeSocket.close();
        outToClient.close();
        inFromClient.close();

        
    }
    
    
    public byte[] receive(boolean closeSocket) throws IOException{
        //verificar se é possível iniciar o servidor no construtor
        byte[] receivedMessage = null;
        welcomeSocket = new ServerSocket(portNumber);
        connectionSocket = welcomeSocket.accept();
        outToClient = new DataOutputStream(connectionSocket.getOutputStream());
        inFromClient = new DataInputStream(connectionSocket.getInputStream());
        
        receivedMessageSize = inFromClient.readInt();
        receivedMessage = new byte[receivedMessageSize];
        inFromClient.read(receivedMessage,0,receivedMessageSize);
        
         if(closeSocket == true){
            
            connectionSocket.close();
            welcomeSocket.close();
            outToClient.close();
            inFromClient.close();
            
        }
       
        return receivedMessage;
        
    }
    
    
    
    
}
