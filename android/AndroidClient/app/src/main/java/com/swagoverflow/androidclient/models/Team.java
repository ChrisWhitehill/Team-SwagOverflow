package com.swagoverflow.androidclient.models;

/**
 * Created by Mike on 2/27/2016.
 */
public class Team {
    private long id;
    private String name;
    private String logo_url;
    private String league;

    public Team(long id, String name, String logo_url) {
        this.id = id;
        this.name = name;
        this.logo_url = logo_url;
    }

    public long getId() {
        return id;
    }

    public String getImageUrl() {
        return logo_url;
    }

    public String getName() {
        return name;
    }

    public String getLeague() {
        return league;
    }
}
