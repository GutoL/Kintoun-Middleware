/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor.api;

import com.google.gson.Gson;

/**
 * Class that contains informantions about OpenStack security.
 * @author gprt
 */
public class Credentials {
    private String tenantName;
    private PasswordCredentials passwordCredentials;


    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenant) {
        this.tenantName = tenant;
    }

    public PasswordCredentials getPasswordCredentials(){
        return this.passwordCredentials;
    }
    
    public void setPasswordCredentials(PasswordCredentials passwordCredentials){
        this.passwordCredentials=passwordCredentials;
    }
    
    public String asString(){
        
        Gson gson=new Gson();
        
        return null;
    }
}
