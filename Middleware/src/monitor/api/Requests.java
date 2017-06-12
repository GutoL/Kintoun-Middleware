/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gprt
 */
public class Requests {
    
    public HttpResponse<JsonNode> requestToken(String jsonCredentials){
        URL url = new URL();
        try {
            return Unirest.post(url.getIdentityTokens())
                    .header("Content-Type", "application/json")
                    .body(jsonCredentials)
                    .asJson();
        } catch (UnirestException ex) {
            Logger.getLogger(Requests.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public HttpResponse<JsonNode> requestServers(String jsonToken){
        URL url = new URL();
        HttpResponse<JsonNode> response=null;
        try{
            response = Unirest.get(url.getServers())
                    .header("X-Auth-Token", jsonToken)
                    .asJson();
            
        }
        catch (UnirestException ex){
            Logger.getLogger(Requests.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return response;
    }
    
    public HttpResponse<JsonNode> requestServer(String jsonToken, String id){
        URL url = new URL();
        HttpResponse<JsonNode> response=null;
        try{
            response = Unirest.get(url.getServers(id))
                    .header("X-Auth-Token", jsonToken)
                    .asJson();
            
        }
        catch (UnirestException ex){
            Logger.getLogger(Requests.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return response;
    }
}
