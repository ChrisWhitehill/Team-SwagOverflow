package com.swagoverflow.androidclient.api.services;

import android.util.Log;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.swagoverflow.androidclient.api.RestApi;
import com.swagoverflow.androidclient.api.requests.DeleteFavoriteShowRequest;
import com.swagoverflow.androidclient.api.requests.DeleteFavoriteTeamRequest;
import com.swagoverflow.androidclient.api.requests.GetShowForUserRequest;
import com.swagoverflow.androidclient.api.requests.GetShowsRequest;
import com.swagoverflow.androidclient.api.requests.GetTeamForUserRequest;
import com.swagoverflow.androidclient.api.requests.GetTeamsRequest;
import com.swagoverflow.androidclient.api.requests.GetUserRequest;
import com.swagoverflow.androidclient.api.requests.ObtainFavoritesRequest;
import com.swagoverflow.androidclient.api.requests.PostFavoriteShowRequest;
import com.swagoverflow.androidclient.api.requests.PostFavoriteTeamRequest;
import com.swagoverflow.androidclient.api.responses.GetShowResponse;
import com.swagoverflow.androidclient.api.responses.GetShowsResponse;
import com.swagoverflow.androidclient.api.responses.GetTeamResponse;
import com.swagoverflow.androidclient.api.responses.GetTeamsResponse;
import com.swagoverflow.androidclient.api.responses.GetUserResponse;
import com.swagoverflow.androidclient.api.responses.ObtainFavoritesResult;
import com.swagoverflow.androidclient.api.responses.ShowPostedResponse;
import com.swagoverflow.androidclient.api.responses.TeamPostedResponse;

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

    @Subscribe
    public void onGetTeams(GetTeamsRequest request) {
        mApi.getAllTeams(new Callback<GetTeamsResponse>() {
            @Override
            public void success(GetTeamsResponse getTeamsResponse, Response response) {
                mBus.post(getTeamsResponse);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());
            }
        });
    }

    @Subscribe
    public void onGetUser(GetUserRequest request) {
        mApi.getUser(request.getId(), new Callback<GetUserResponse>() {
            @Override
            public void success(GetUserResponse response, Response response2) {
                mBus.post(response);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());
            }
        });
    }

    @Subscribe
    public void onPostFavoriteTeam(PostFavoriteTeamRequest request) {
        mApi.postTeam(request.getUserId(), request, new Callback<com.squareup.okhttp.Response>() {
            @Override
            public void success(com.squareup.okhttp.Response response, Response response2) {
                Log.i(TAG, "Succeeded in posting team");
                mBus.post(new TeamPostedResponse());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());
            }
        });
    }

    @Subscribe
    public void onDeleteFavoriteTeam(DeleteFavoriteTeamRequest request) {
        mApi.deleteFavoriteTeam(request.getUserId(), request.getTeamId(), new Callback<com.squareup.okhttp.Response>() {
            @Override
            public void success(com.squareup.okhttp.Response response, Response response2) {
                Log.i(TAG, "Succeeded in deleting team");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());
            }
        });
    }

    @Subscribe
    public void onGetShows(GetShowsRequest request) {
        mApi.getAllShows(new Callback<GetShowsResponse>() {
            @Override
            public void success(GetShowsResponse response, Response response2) {
                mBus.post(response);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());
            }
        });
    }

    @Subscribe
    public void onPostShow(PostFavoriteShowRequest request){
        mApi.postShow(request.getUserId(), request, new Callback<com.squareup.okhttp.Response>() {
            @Override
            public void success(com.squareup.okhttp.Response response, Response response2) {
                Log.i(TAG, "Success");
                mBus.post(new ShowPostedResponse());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());
            }
        });
    }

    @Subscribe
    public void onDeleteShow(DeleteFavoriteShowRequest request) {
        mApi.deleteFavoriteShow(request.getUserId(), request.getShowId(), new Callback<com.squareup.okhttp.Response>() {
            @Override
            public void success(com.squareup.okhttp.Response response, Response response2) {
                Log.i(TAG, "deleted");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());
            }
        });
    }
}
