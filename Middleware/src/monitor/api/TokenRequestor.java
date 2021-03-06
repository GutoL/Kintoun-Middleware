/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor.api;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import java.util.logging.Level;
import java.util.logging.Logger;
import monitor.GetInfo;
import org.json.JSONException;

/**
 * Class that requests token to access OpenStack.
 * @author gprt
 */
public class TokenRequestor {
    //private String token;
    private Credentials credentials;
    private GetInfo info = GetInfo.getInstance();
    
    /**
     * Method that requests token to OpenStack.
     * @return string with token
     */
    public String request(){
        
        //System.out.println("Tenant: "+info.getTenant()+" User: "+info.getUser()+" password: "+info.getPassword());
        
        this.credentials=new Credentials();
       
        this.credentials.setTenantName(info.getTenant());
        
        this.credentials.setPasswordCredentials(new PasswordCredentials(info.getUser(),info.getPassword()));
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
        
        return tokenId;
    }
    
}
