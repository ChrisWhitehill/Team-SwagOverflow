package com.swagoverflow.androidclient.api.requests;

/**
 * Created by Mike on 2/27/2016.
 */
public class GetTeamForUserRequest {
    private long userId;
    private long teamId;

    public GetTeamForUserRequest(long userId, long teamId) {
        this.userId = userId;
        this.teamId = teamId;
    }

    public long getUserId() {
        return userId;
    }

    public long getTeamId() {
        return teamId;
    }
}
