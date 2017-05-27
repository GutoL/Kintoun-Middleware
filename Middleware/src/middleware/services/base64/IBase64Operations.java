/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.base64;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Demis e Lucas
 */
public interface IBase64Operations extends Remote{
    public String encode(String s) throws RemoteException;
    public String decode(String s) throws RemoteException;
    
}
