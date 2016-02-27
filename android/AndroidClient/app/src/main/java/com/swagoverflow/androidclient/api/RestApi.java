package com.swagoverflow.androidclient.api;

import com.squareup.okhttp.Response;
import com.swagoverflow.androidclient.api.requests.PostFavoriteTeamRequest;
import com.swagoverflow.androidclient.api.responses.GetShowResponse;
import com.swagoverflow.androidclient.api.responses.GetTeamResponse;
import com.swagoverflow.androidclient.api.responses.GetTeamsResponse;
import com.swagoverflow.androidclient.api.responses.GetUserResponse;
import com.swagoverflow.androidclient.api.responses.ObtainFavoritesResult;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface RestApi {
    @GET("/user/{user_id}/favorites")
    void getFavorites(@Path("user_id") long userId, Callback<ObtainFavoritesResult> response);

    @GET("/user/{user_id}")
    void getUser(@Path("user_id") long userId, Callback<GetUserResponse> response);

    @GET("/team")
    void getAllTeams(Callback<GetTeamsResponse> response);

    @GET("/user/{user_id}/shows/{show_id}")
    void getShow(@Path("user_id") long userId, @Path("show_id") long showId, Callback<GetShowResponse> response);

    @GET("/user/{user_id}/teams/{team_id}")
    void getTeam(@Path("user_id") long userId, @Path("team_id") long teamId, Callback<GetTeamResponse> response);

    @POST("/user/{user_id}/teams/")
    void postTeam(@Path("user_id") long userId, @Body PostFavoriteTeamRequest request, Callback<Response> response);

    @DELETE("/user/{user_id}/teams/{team_id}")
    void deleteFavoriteTeam(@Path("user_id") long userId, @Path("team_id") long teamId, Callback<Response> response);
}
