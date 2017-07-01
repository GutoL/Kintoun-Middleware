/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.util;

import java.io.IOException;

/**
 * Interface that contains the methods used by Marshaller
 * 
 * marshall and unmarshall
 * @author Demis
 */
public interface IMarshaller {
    public byte[] marshall(Object msg) throws IOException, InterruptedException;
 
    public Object unmarshall(byte[] msg);
}
