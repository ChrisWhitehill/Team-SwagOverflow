package com.swagoverflow.androidclient.models;

import java.util.Date;

/**
 * Created by Mike on 2/27/2016.
 */
public class Episode {
    private Show show;
    private Date date;
    private String channel_number;
    private String channel_name;
    private String video_url;
    private String thumbnail_url;

    public Date getDate() {
        return date;
    }

    public String getChannel() {
        return channel_name;
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
