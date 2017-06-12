/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor.api;

/**
 *
 * @author gprt
 */
public class PasswordCredentials {
    private String password;
    private String username;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public PasswordCredentials(String name, String password){
        this.username=name;
        this.password= password;
    }
    
}