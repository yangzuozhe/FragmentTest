package com.example.fragmenttest;

import java.io.Serializable;

public class MyBean implements Serializable {
    private String selectName;
    private String fragmentName;

    public MyBean(String selectName, String fragmentName) {
        this.selectName = selectName;
        this.fragmentName = fragmentName;
    }

    public String getSelectName() {
        return selectName;
    }

    public void setSelectName(String selectName) {
        this.selectName = selectName;
    }

    public String getFragmentName() {
        return fragmentName;
    }

    public void setFragmentName(String fragmentName) {
        this.fragmentName = fragmentName;
    }
}
