/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.services;


/**
 * helper to base 64 operations helper
 * @author Demis
 */
public class Base64OperationsHelper {
    /**
     * reads the message. If contains D, means decode. Else, encode.
     * @param message
     * @return
     * @throws Throwable 
     */
    public String readMessage(String message) throws Throwable{
        String[]arrayMessage=message.split(" ");
        String response="";
        IBase64Operations base64Op=new Base64Operations();
        if(arrayMessage[0].equals("D")){
            response=base64Op.decode(arrayMessage[1]);
        }
        else{
            response=base64Op.encode(arrayMessage[1]);
        }
        return response;
    }
}
