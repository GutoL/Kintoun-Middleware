/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.services;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.rmi.server.RemoteObject;
import java.rmi.server.UnicastRemoteObject;
import java.util.Base64;

/**
 *
 * @author Demis e Lucas
 */
public class Base64Operations extends UnicastRemoteObject implements IBase64Operations{
    public Base64Operations()throws RemoteException{
        super();
    }
    
    @Override
    public String encode(String s){
        byte[] encodedBytes = Base64.getEncoder().encode(s.getBytes());
        String response=new String(encodedBytes);
        return response;
    }
    
    @Override
    public String decode(String s){
        byte[] decodedBytes = Base64.getDecoder().decode(s.getBytes());
        return new String(decodedBytes);
    }
}
