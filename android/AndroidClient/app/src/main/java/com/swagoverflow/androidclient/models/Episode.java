package com.swagoverflow.androidclient.models;

import java.util.Date;

/**
 * Created by Mike on 2/27/2016.
 */
public class Episode {
    private Show show;
    private Date date;
    private String channel_number;
    private String video_url;
    private String thumbnail_url;

    public Date getDate() {
        return date;
    }

    public String getChannel() {
        return channel_number;
    }

    public String getVideoUrl() {
        return video_url;
    }

    public String getThumbnailUrl() {
        return thumbnail_url;
    }

    public Show getShow() {
        return show;
    }
}
