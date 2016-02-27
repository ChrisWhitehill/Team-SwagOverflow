package com.swagoverflow.androidclient.models;

/**
 * Created by Mike on 2/27/2016.
 */
public class TeamFavorite {
    private Team team;
    private User user;
    private boolean notifications;
    private long id;

    public Team getTeam() {
        return team;
    }

    public User getUser() {
        return user;
    }

    public boolean isNotifications() {
        return notifications;
    }

    public long getId() {
        return id;
    }
}
