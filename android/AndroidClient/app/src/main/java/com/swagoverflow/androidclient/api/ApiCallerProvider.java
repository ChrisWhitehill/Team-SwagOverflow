package com.swagoverflow.androidclient.api;

public class ApiCallerProvider {
    private static IApiCaller mApiCaller;

    private ApiCallerProvider() { }

    public static IApiCaller getInstance() {
        if (mApiCaller == null) {
            mApiCaller = new ApiCaller();
        }

        return mApiCaller;
    }
}
