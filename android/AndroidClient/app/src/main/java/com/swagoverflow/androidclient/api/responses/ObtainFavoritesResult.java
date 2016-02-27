package com.swagoverflow.androidclient.api.responses;

import com.swagoverflow.androidclient.models.ShowFavorite;
import com.swagoverflow.androidclient.models.TeamFavorite;

import java.util.List;

/**
 * Created by Mike on 2/27/2016.
 */
public class ObtainFavoritesResult {
    private List<ShowFavorite> shows;
    private List<TeamFavorite> teams;

    public List<ShowFavorite> getShows() {
        return shows;
    }

    public List<TeamFavorite> getTeams() {
        return teams;
    }
}
