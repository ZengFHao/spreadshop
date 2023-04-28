package com.zfh.jdbc1111.TestSpringBoot111.model;
// http://hocalhost:8080/register?username=zfh&password=666
public class Register {
    private String username;
    private String password;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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



}
