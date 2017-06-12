/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor.api;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;

/**
 *
 * @author gprt
 */
public class TokenRequestor {
    private String token;
    private Credentials credentials;
    
    public String request(){
        
        this.credentials=new Credentials();
        this.credentials.setTenantName("demo");// muda para o do projeto
        this.credentials.setPasswordCredentials(new PasswordCredentials("admin", "root")); //muda para o do projeto
        Auth auth=new Auth(credentials);
        
        Requests requests = new Requests();
        Gson gson=new Gson();
        String jsonCredentials=gson.toJson(auth);
        URL url=new URL();
        String serverIP= url.getServerIP();
        HttpResponse<JsonNode> jsonResponse=requests.requestToken(jsonCredentials);
        String tokenId=null;
        try {
            tokenId = jsonResponse.getBody().getObject().getJSONObject("access").getJSONObject("token").getString("id");
        } catch (JSONException ex) {
            Logger.getLogger(TokenRequestor.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("monitor.api.TokenRequestor.request() "+tokenId); 
        return tokenId;
    }
    
}
