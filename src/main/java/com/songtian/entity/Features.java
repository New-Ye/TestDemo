package com.songtian.entity;

public class Features {
    private String features;
    private String slavename;

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getSlavename() {
        return slavename;
    }

    public void setSlavename(String slavename) {
        this.slavename = slavename;
    }


    @Override
    public String toString() {
        return features;
    }
}
