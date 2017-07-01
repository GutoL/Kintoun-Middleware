/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.naming;

import java.util.ArrayList;
import middleware.client.ClientProxy;

/**
 * Interface that defines the methods to be implemented by Naming Proxy and Naming Implementation
 * Comprises five methods:
 * bind: register a remote object
 * unbind: deactivate a remote object
 * lookup: Request the location of a remote object
 * reactivate: used to unpause instances and reactivate remote object
 * list: list the remote objects saved
 * 
 * @author Demis
 */
public interface INaming {
    public void bind(String serviceName, ClientProxy clientProxy);
    public void unbind(String serviceName, ClientProxy clientProxy);
    public ClientProxy lookup(String serviceName);
    public void reactivate(String serviceName, ClientProxy clientProxy);
    public ArrayList<String> list();
    
}
