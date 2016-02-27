package com.swagoverflow.androidclient.api.requests;

/**
 * Created by Mike on 2/27/2016.
 */
public class PostFavoriteTeamRequest {
    private long team;
    private long user;
    private boolean notifications = true;

    public PostFavoriteTeamRequest(long teamId, long userId) {
        this.team = teamId;
        this.user = userId;
    }

    public long getTeamId() {
        return team;
    }

    public long getUserId() {
        return user;
    }
}
