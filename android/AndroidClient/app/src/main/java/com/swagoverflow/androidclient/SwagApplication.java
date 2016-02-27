package com.swagoverflow.androidclient;

import android.app.Application;

import com.swagoverflow.androidclient.models.User;

/**
 * Created by Mike on 2/27/2016.
 */
public class SwagApplication extends Application {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
