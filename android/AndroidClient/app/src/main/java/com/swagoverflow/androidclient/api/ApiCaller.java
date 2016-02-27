package com.swagoverflow.androidclient.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.otto.Bus;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * An example of how to implement IApiCaller
 * This example used Otto for a pub/sub event system
 */
public class ApiCaller implements IApiCaller {
    private Bus mBus;
    private RestApi mApi;

    ApiCaller() {
        mBus = BusProvider.getInstance();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("https://example.com")
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        mApi = adapter.create(RestApi.class);
    }

    @Override
    public void obtainData(Object object) {
        // TODO check for internet connection if needed
        mBus.post(object);
    }

    @Override
    public void registerObject(Object object) {
        mBus.register(object);
    }

    @Override
    public void unregisterObject(Object object) {
        mBus.unregister(object);
    }
}
