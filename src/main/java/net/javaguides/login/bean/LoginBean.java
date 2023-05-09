package net.javaguides.login.bean;

import java.io.Serializable;

public class LoginBean implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
//This declares a public method called setPassword() that takes a String parameter.
//This method is used to set the value of the password field
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}