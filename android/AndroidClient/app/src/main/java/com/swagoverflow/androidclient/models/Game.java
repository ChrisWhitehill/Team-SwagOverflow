package com.swagoverflow.androidclient.models;

import java.util.Date;

/**
 * Created by Mike on 2/27/2016.
 */
public class Game {
    private Date date;
    private String channel_number;
    private String channel_name;
    private String video_url;
    private String thumbnail_url;
    private Team home_team;
    private Team away_team;

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

    public String getDescription() {
        return home_team.getName() + " vs. " + away_team.getName();
    }
}
