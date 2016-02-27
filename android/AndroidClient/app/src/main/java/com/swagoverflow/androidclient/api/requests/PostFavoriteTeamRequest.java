package com.swagoverflow.androidclient.api.requests;

/**
 * Created by Mike on 2/27/2016.
 */
public class PostFavoriteTeamRequest {
    private long teamId;
    private long userId;

    public PostFavoriteTeamRequest(long teamId, long userId) {
        this.teamId = teamId;
        this.userId = userId;
    }

    public long getTeamId() {
        return teamId;
    }

    public long getUserId() {
        return userId;
    }
}
