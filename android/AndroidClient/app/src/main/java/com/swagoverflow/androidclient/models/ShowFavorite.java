package com.swagoverflow.androidclient.models;

/**
 * Created by Mike on 2/27/2016.
 */
public class ShowFavorite {
    private Show show;
    private User user;
    private boolean notifications;
    private long id;

    public Show getShow() {
        return show;
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
