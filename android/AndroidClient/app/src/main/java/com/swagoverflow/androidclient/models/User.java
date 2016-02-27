package com.swagoverflow.androidclient.models;

import java.util.List;

/**
 * Created by Mike on 2/27/2016.
 */
public class User {
    private long id;
    private String name;
    private String email;
    private String phone;

    private List<ShowFavorite> favoriteShows;
    private List<TeamFavorite> favoriteTeams;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public List<ShowFavorite> getFavoriteShows() {
        return favoriteShows;
    }

    public List<TeamFavorite> getFavoriteTeams() {
        return favoriteTeams;
    }

    public void setFavoriteShows(List<ShowFavorite> favoriteShows) {
        this.favoriteShows = favoriteShows;
    }

    public void setFavoriteTeams(List<TeamFavorite> favoriteTeams) {
        this.favoriteTeams = favoriteTeams;
    }

    public void addFavoriteTeam(TeamFavorite favorite) {
        this.favoriteTeams.add(favorite);
    }

    public void addFavoriteShow(ShowFavorite favorite) {
        this.favoriteShows.add(favorite);
    }

    public TeamFavorite getFavoriteTeamByTeamId(long id) {
        for (TeamFavorite t : favoriteTeams) {
            if (id == t.getTeam().getId()) {
                return t;
            }
        }
        return null;
    }

    public ShowFavorite getFavoriteShowByShowId(long id) {
        for (ShowFavorite s : favoriteShows) {
            if (id == s.getShow().getId()) {
                return s;
            }
        }

        return null;
    }
}
