/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *transform objects to bytes (marshalling) and bytes to objects (unmarshalling)
 * 
 * @author Demis
 */
public class Marshaller implements IMarshaller{

    /**
     * transform object to bytes
     * @param msg an objecto to be marshalled
     * @return bytes
     * @throws IOException
     * @throws InterruptedException 
     */
    @Override
    public byte[] marshall(Object msg) throws IOException, InterruptedException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
        objectStream.writeObject(msg);
 
        return byteStream.toByteArray();
    }

    /**
     * transform bytes to objects
     * @param msg bytes to be unmarshalled
     * @return object unmarshalled
     */
    @Override
    public Object unmarshall(byte[] msg) {
     
        Object message = null;
 
        ByteArrayInputStream byteStream = new ByteArrayInputStream(msg);
        ObjectInputStream objectStream = null;
        try {
            objectStream = new ObjectInputStream(byteStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        try {
            message = (Object) objectStream.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        return message;
    }
    
}
