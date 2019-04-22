package com.songtian.entity;

public class Slave {
    private String username;
    private String slavename;
    private String features;

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    private User user;

    @Override
    public String toString() {
        return slavename+features;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSlavename() {
        return slavename;
    }

    public void setSlavename(String slavename) {
        this.slavename = slavename;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
