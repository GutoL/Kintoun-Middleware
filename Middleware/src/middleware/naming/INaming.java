/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.naming;

import java.util.ArrayList;
import middleware.client.ClientProxy;

/**
 *
 * @author gprt
 */
public interface INaming {
    public void bind(String serviceName, ClientProxy clientProxy);
    public ClientProxy lookup(String serviceName);
    public ArrayList<String> list();
    
}
