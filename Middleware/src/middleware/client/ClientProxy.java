/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.client;

import java.io.Serializable;

/**
 *
 * @author Demis e Lucas
 */
public class ClientProxy implements Serializable{

    public ClientProxy(String host, int port) {
        this.host = host;
        this.port = port;
    }
    
    public ClientProxy(){
        
    }
    protected String host;
    protected int port;
    private int objectId;

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

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }
    
}
