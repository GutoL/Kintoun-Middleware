/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.util;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Body of request message
 * 
 * parameters: parameters of method to be invoked
 * @author Demis
 */
public class RequestBody implements Serializable{
    private ArrayList<Object> parameters;

    public ArrayList<Object> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<Object> parameters) {
        this.parameters = parameters;
    }

    public RequestBody(ArrayList<Object> parameters) {
        this.parameters=parameters;
    }
    
    
}
