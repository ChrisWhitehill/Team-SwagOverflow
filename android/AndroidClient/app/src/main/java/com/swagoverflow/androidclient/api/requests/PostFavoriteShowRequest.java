package com.swagoverflow.androidclient.api.requests;

public class PostFavoriteShowRequest {
    private long team;
    private long user;
    private boolean notifications = true;

    public PostFavoriteShowRequest(long showId, long userId) {
        this.team = showId;
        this.user = userId;
    }

    public long getShowId() {
        return team;
    }

    public long getUserId() {
        return user;
    }
}
