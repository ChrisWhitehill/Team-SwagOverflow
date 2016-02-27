package com.swagoverflow.androidclient.models;

import java.util.Date;

/**
 * Created by Mike on 2/27/2016.
 */
public class Game {
    private Date date;
    private String channel;
    private String videoUrl;
    private String thumbnailUrl;
    private Team homeTeam;
    private Team awayTeam;

    public Date getDate() {
        return date;
    }

    public String getChannel() {
        return channel;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
