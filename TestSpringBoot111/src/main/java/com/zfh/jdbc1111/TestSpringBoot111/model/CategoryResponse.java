package com.zfh.jdbc1111.TestSpringBoot111.model;

import java.util.List;

public class CategoryResponse {
    private Boolean success;
    private List<Category> categories;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
