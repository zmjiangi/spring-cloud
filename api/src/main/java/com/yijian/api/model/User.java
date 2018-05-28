package com.yijian.api.model;

import java.io.Serializable;

/**
 * Created by zmjiangi on 2018/5/28.
 */
public class User implements Serializable {
    private static final long serialVersionUID = -5658286152262095481L;

    private String name;
    private String password;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
