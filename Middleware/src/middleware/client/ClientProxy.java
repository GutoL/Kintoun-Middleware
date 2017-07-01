/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. test
 */
package middleware.client;

import java.io.Serializable;

/**
 * Client Proxy  
 * Provides transparency location of remote object
 * Implements serializable in order to be marshalled/unmarshalled
 * 
 * host: Address of remote object
 * port: Port in which service is running
 * paused: indicates if the service can be acessed. Paused is false 
 * by default, allowing the remote object at the host to be accessed. 
 * True denotes that the object becomes unaccessible due to a pause 
 * in the instance.
 * @author Demis
 */
public class ClientProxy implements Serializable{

    public ClientProxy(String host, int port) {
        this.host = host;
        this.port = port;
        this.paused=false;
    }
    
    public ClientProxy(){
        
    }
    protected String host;
    protected int port;
    private int objectId;
    private boolean paused;

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
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

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }
    
}