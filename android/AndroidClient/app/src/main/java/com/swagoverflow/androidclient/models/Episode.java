package com.swagoverflow.androidclient.models;

import java.util.Date;

/**
 * Created by Mike on 2/27/2016.
 */
public class Episode {
    private Show show;
    private Date date;
    private String channel;
    private String videoUrl;
    private String thumbnailUrl;

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

    public Show getShow() {
        return show;
    }
}
