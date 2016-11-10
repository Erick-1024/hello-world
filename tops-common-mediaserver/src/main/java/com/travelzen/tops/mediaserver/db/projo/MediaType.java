package com.travelzen.tops.mediaserver.db.projo;


public enum MediaType {

    IMAGE("image"),
    CONTRACT("contract"),
    DOWNLOAD("download"),
    VIDEO("video");

    private final String value;

    MediaType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
