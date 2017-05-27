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
 *
 * @author Demis e Lucas
 */
public class Marshaller implements IMarshaller{

    @Override
    public byte[] marshall(Object msg) throws IOException, InterruptedException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
        objectStream.writeObject(msg);
 
        return byteStream.toByteArray();
    }

    @Override
    public Object unmarshall(byte[] msg) {
     // log
        // logger.info("start");
 
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
 
        // log
        // logger.info("complete");
 
        return message;
    }
    
}
