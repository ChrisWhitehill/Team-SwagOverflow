package com.swagoverflow.androidclient.api.services;

import android.util.Log;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.swagoverflow.androidclient.api.RestApi;
import com.swagoverflow.androidclient.api.requests.GetShowForUserRequest;
import com.swagoverflow.androidclient.api.requests.GetTeamForUserRequest;
import com.swagoverflow.androidclient.api.requests.ObtainFavoritesRequest;
import com.swagoverflow.androidclient.api.responses.GetShowResponse;
import com.swagoverflow.androidclient.api.responses.GetTeamResponse;
import com.swagoverflow.androidclient.api.responses.ObtainFavoritesResult;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Mike on 2/27/2016.
 */
public class RequestService {
    private Bus mBus;
    private RestApi mApi;
    private String TAG = "RequestService";

    public RequestService(Bus mBus, RestApi mApi) {
        this.mBus = mBus;
        this.mApi = mApi;
    }

    @Subscribe
    public void onObtainFavorites(ObtainFavoritesRequest request) {
        mApi.getFavorites(request.getUserId(), new Callback<ObtainFavoritesResult>() {
            @Override
            public void success(ObtainFavoritesResult obtainFavoritesResult, Response response) {
                mBus.post(obtainFavoritesResult);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());
            }
        });
    }

    @Subscribe
    public void onGetShowForUser(GetShowForUserRequest request) {
        mApi.getShow(request.getUserId(), request.getShowId(), new Callback<GetShowResponse>() {
            @Override
            public void success(GetShowResponse getShowResponse, Response response) {
                mBus.post(getShowResponse);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());
            }
        });
    }

    @Subscribe
    public void onGetTeamForUser(GetTeamForUserRequest request) {
        mApi.getTeam(request.getUserId(), request.getTeamId(), new Callback<GetTeamResponse>() {
            @Override
            public void success(GetTeamResponse getTeamResponse, Response response) {
                mBus.post(getTeamResponse);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());
            }
        });
    }
}
