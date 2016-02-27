package com.swagoverflow.androidclient.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.otto.Bus;
import com.swagoverflow.androidclient.api.services.RequestService;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class ApiCaller implements IApiCaller {
    private Bus mBus;
    private RestApi mApi;
    private RequestService mRequestService;

    ApiCaller() {
        mBus = BusProvider.getInstance();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://swagoverflow.brobin.me/api")
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        mApi = adapter.create(RestApi.class);

        mRequestService = new RequestService(mBus, mApi);
        registerObject(mRequestService);
    }

    @Override
    public void obtainData(Object object) {
        // TODO check for internet connection if needed
        mBus.post(object);
    }

    @Override
    public void registerObject(Object object) {
        try {
            mBus.register(object);
        } catch (Exception e) { }
    }

    @Override
    public void unregisterObject(Object object) {
        try {
            mBus.unregister(object);
        } catch (Exception e) { }
    }
}
