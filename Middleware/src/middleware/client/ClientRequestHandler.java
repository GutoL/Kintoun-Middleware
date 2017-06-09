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
 *
 * @author Demis e Lucas
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
    
  public void send(byte[] msg) throws IOException, InterruptedException{
      //inicializando sockets e streams
      clientSocket = new Socket(this.host,this.port);
      outToServer = new DataOutputStream(clientSocket.getOutputStream());
      inFromServer = new DataInputStream(clientSocket.getInputStream());
      
      //construindo e enviando a mensagem
      sentMessageSize = msg.length;
      outToServer.writeInt(sentMessageSize);
      outToServer.write(msg,0,sentMessageSize);
      outToServer.flush();
      
 
  }
  
  
  public byte[] receive() throws IOException{
      byte[] msg = null;
      System.out.println("middleware.client.ClientRequestHandler.receive() "+inFromServer);
      receiveMessageSize = inFromServer.readInt();
      msg = new byte[receiveMessageSize];
      inFromServer.read(msg,0,receiveMessageSize);
      
      //verificar se dá problemas posteriores
      clientSocket.close();
      outToServer.close();
      inFromServer.close();
      
      return msg;
  }
  
  
   
  public void send(byte[] msg, boolean closeSocket) throws IOException, InterruptedException{
      //inicializando sockets e streams
      clientSocket = new Socket(this.host,this.port);
      outToServer = new DataOutputStream(clientSocket.getOutputStream());
      inFromServer = new DataInputStream(clientSocket.getInputStream());
      
      //construindo e enviando a mensagem
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
