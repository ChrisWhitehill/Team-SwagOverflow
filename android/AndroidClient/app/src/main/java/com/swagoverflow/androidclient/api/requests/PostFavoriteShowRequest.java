package com.swagoverflow.androidclient.api.requests;

public class PostFavoriteShowRequest {
    private long show;
    private long user;
    private boolean notifications = true;

    public PostFavoriteShowRequest(long showId, long userId) {
        this.show = showId;
        this.user = userId;
    }

    public long getShowId() {
        return show;
    }

    public long getUserId() {
        return user;
    }
}
