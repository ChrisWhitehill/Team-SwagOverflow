package com.swagoverflow.androidclient.models;

/**
 * Created by Mike on 2/27/2016.
 */
public class Show {
    private long id;
    private String name;
    private String logo_url;

    public Show(long id, String name, String logo_url) {
        this.id = id;
        this.name = name;
        this.logo_url = logo_url;
    }

    public String getImageUrl() {
        return logo_url;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
