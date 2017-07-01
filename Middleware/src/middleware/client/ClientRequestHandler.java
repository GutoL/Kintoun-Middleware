/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Client Request Handler
 * Sends requisitions to another host
 * Only use bytes in its messages
 * 
 * host: Address of host that will receive the message
 * port: Destination port 
 * sentMessageSize: Length of sent message (bytes) to create the buffer when sent
 * receiveMessageSize: Length of received message (bytes) to create the buffer when received
 * clientSocket: Socket used to sent/receive messages
 * outToServer: Stream used to send messages to destination (only bytes)
 * inFromServer: Stream used to receive messages from destination (only bytes)
 * 
 * @author Demis
 */
public class ClientRequestHandler {
    private String host;
    private int port;
    private int sentMessageSize;
    private int receiveMessageSize;
    
    //sockets
    private Socket clientSocket = null;
    private DataOutputStream outToServer=null;
    private DataInputStream inFromServer = null;

    public ClientRequestHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

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
    
    /**
     * send message to a host
     * @param msg bytes
     * @throws IOException
     * @throws InterruptedException 
     */
    
  public void send(byte[] msg) throws IOException, InterruptedException{
      //initialize sockets and streams
      clientSocket = new Socket(this.host,this.port);
      outToServer = new DataOutputStream(clientSocket.getOutputStream());
      inFromServer = new DataInputStream(clientSocket.getInputStream());
      
      //building and sending the message
      sentMessageSize = msg.length;
      outToServer.writeInt(sentMessageSize);
      outToServer.write(msg,0,sentMessageSize);
      outToServer.flush();
      
 
  }
  
  /**
   * receives a reply message
   * @return msg (bytes)
   * @throws IOException 
   */
  public byte[] receive() throws IOException{
      byte[] msg = null;
      
      receiveMessageSize = inFromServer.readInt();
      msg = new byte[receiveMessageSize];
      inFromServer.read(msg,0,receiveMessageSize);
      
      clientSocket.close();
      outToServer.close();
      inFromServer.close();
      
      return msg;
  }
  
  
   
  public void send(byte[] msg, boolean closeSocket) throws IOException, InterruptedException{
      clientSocket = new Socket(this.host,this.port);
      outToServer = new DataOutputStream(clientSocket.getOutputStream());
      inFromServer = new DataInputStream(clientSocket.getInputStream());
      
      sentMessageSize = msg.length;
      outToServer.writeInt(sentMessageSize);
      outToServer.write(msg,0,sentMessageSize);
      outToServer.flush();
      
      if(closeSocket == true){
        clientSocket.close();
        outToServer.close();
        inFromServer.close();
      }
      
  }
  
  
  
  
}
