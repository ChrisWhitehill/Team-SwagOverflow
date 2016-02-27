package com.swagoverflow.androidclient.api.requests;

/**
 * Created by Mike on 2/27/2016.
 */
public class GetShowForUserRequest {
    private long userId;
    private long showId;

    public GetShowForUserRequest(long userId, long showId) {
        this.userId = userId;
        this.showId = showId;
    }

    public long getUserId() {
        return userId;
    }

    public long getShowId() {
        return showId;
    }
}
