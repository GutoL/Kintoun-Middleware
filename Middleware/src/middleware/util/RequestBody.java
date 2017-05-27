/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.util;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Demis e Lucas
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
