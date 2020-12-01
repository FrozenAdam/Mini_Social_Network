/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.dtos;

import java.io.Serializable;

/**
 *
 * @author theFrozenAdam
 */
public class RegistrationDTO implements Serializable {

    private String username, password, fullname;
    private int status;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String username, String fullname, int status) {
        this.username = username;
        this.fullname = fullname;
        this.status = status;
    }
    
    public RegistrationDTO(String username, String fullname, String password, int status){
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
