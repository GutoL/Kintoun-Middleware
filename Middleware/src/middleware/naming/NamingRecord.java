/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.naming;

import middleware.client.ClientProxy;

/**
 *
 * @author gprt
 */
public class NamingRecord {
    private String serviceName;
    private ClientProxy clientProxy;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public ClientProxy getClientProxy() {
        return clientProxy;
    }

    public void setClientProxy(ClientProxy clientProxy) {
        this.clientProxy = clientProxy;
    }

    public NamingRecord(String serviceName, ClientProxy clientProxy) {
        this.serviceName = serviceName;
        this.clientProxy = clientProxy;
    }
}
