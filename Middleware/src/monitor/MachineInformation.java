/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitor;

/**
 *
 * @author guto
 */
public class MachineInformation {
    
    String IP;
    String name;
    String id;
    int port;
    
    public MachineInformation() {
    }

    public MachineInformation(String IP, String name, int port) {
        this.IP = IP;
        this.name = name;
        this.port = port;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getIP() {
        return IP;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public int getPort() {
        return port;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    

    
    
    
    
}
