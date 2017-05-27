/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.services;

/**
 *
 * @author Demis e Lucas
 */
public interface IBase64Operations{
    public String encode(String s) throws Throwable;
    public String decode(String s) throws Throwable;
    
}
