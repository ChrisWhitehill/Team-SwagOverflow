package com.swagoverflow.androidclient.api.responses;

import com.swagoverflow.androidclient.models.Episode;
import com.swagoverflow.androidclient.models.Game;

import java.util.List;

/**
 * Created by Mike on 2/27/2016.
 */
public class GetEventsForUserResponse {
    private List<Game> games;
    private List<Episode> episodes;

    public List<Game> getGames() {
        return games;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }
}
