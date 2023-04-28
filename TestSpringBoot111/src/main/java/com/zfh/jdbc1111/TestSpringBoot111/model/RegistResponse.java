package com.zfh.jdbc1111.TestSpringBoot111.model;

public class RegistResponse {
    private  boolean isSuccess;

    private Register register;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }
}
