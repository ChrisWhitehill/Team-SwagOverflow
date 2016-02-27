package com.swagoverflow.androidclient.api.requests;

/**
 * Created by Mike on 2/27/2016.
 */
public class GetUserRequest {
    private long id;

    public GetUserRequest(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
