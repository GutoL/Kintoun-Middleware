/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor.api;

/**
 * Class that contais credentials about OpenStack evironment.
 * @author gprt
 */
public class Auth {
    private Credentials auth;

    public Credentials getCredentials() {
        return auth;
    }

    public void setCredentials(Credentials credentials) {
        this.auth = credentials;
    }
    
    public Auth(Credentials credentials){
        this.auth=credentials;
    }
    
}
