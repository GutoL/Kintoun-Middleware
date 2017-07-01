/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.util;

import java.util.ArrayList;

/**
 * gather the parameters to invoke the remote object
 *
 * object id: id of the remote object
 * ipAddress: address of host of remote object
 * portNumber: port of remote object
 * operationName: name of method to be invoked in remote object
 * parameters: parameters of method to be invoked
 * 
 * @author Demis
 */
public class Invocation {
    private int objectId;
    private String ipAddress;
    private int portNumber;
    private String operationName;
    private ArrayList<Object> parameters;

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public ArrayList<Object> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<Object> parameters) {
        this.parameters = parameters;
    }
    
}
