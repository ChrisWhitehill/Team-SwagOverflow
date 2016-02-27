package com.swagoverflow.androidclient.api.requests;

/**
 * Created by Mike on 2/27/2016.
 */
public class GetEventsForUserRequest {
    private long userId;

    public GetEventsForUserRequest(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }
}
