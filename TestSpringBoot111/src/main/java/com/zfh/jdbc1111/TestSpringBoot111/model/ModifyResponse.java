package com.zfh.jdbc1111.TestSpringBoot111.model;

public class ModifyResponse {
    private  boolean isSuccess;

    private Modify modify;



    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public Modify getModify() {
        return modify;
    }

    public void setModify(Modify modify) {
        this.modify = modify;
    }
}
