package com.swagoverflow.androidclient.api.responses;

import com.swagoverflow.androidclient.models.Team;

import java.util.List;

/**
 * Created by Mike on 2/27/2016.
 */
public class GetTeamsResponse {
    private List<Team> teams;

    public List<Team> getTeams() {
        return teams;
    }
}
