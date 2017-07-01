/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.services;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Base64;

/**
 * base64 operations
 * encode and decode
 * Implements IBase64Operations
 * @author Demis
 */
public class Base64Operations extends UnicastRemoteObject implements IBase64Operations{
    public Base64Operations()throws RemoteException{
        super();
    }
    
    /**
     * encodes a string
     * @param s
     * @return 
     */
    @Override
    public String encode(String s){
        byte[] encodedBytes = Base64.getEncoder().encode(s.getBytes());
        String response=new String(encodedBytes);
        return response;
    }
    
    /**
     * decodes a string
     * @param s
     * @return 
     */
    @Override
    public String decode(String s){
        byte[] decodedBytes = Base64.getDecoder().decode(s.getBytes());
        return new String(decodedBytes);
    }
}
