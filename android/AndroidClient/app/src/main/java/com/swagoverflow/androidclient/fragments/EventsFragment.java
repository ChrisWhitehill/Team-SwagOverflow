package com.swagoverflow.androidclient.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HeaderViewListAdapter;

import com.squareup.otto.Subscribe;
import com.swagoverflow.androidclient.R;
import com.swagoverflow.androidclient.SwagApplication;
import com.swagoverflow.androidclient.api.ApiCallerProvider;
import com.swagoverflow.androidclient.api.IApiCaller;
import com.swagoverflow.androidclient.api.requests.GetEventsForUserRequest;
import com.swagoverflow.androidclient.api.responses.GetEventsForUserResponse;
import com.swagoverflow.androidclient.models.Episode;
import com.swagoverflow.androidclient.models.Game;
import com.swagoverflow.androidclient.models.User;

import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class EventsFragment extends Fragment {

    private IApiCaller apiCaller;
    private List<Game> games;
    private List<Episode> episodes;
    private

    public EventsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_events, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        User user = ((SwagApplication) getActivity().getApplication()).getUser();
        apiCaller = ApiCallerProvider.getInstance();
        apiCaller.obtainData(new GetEventsForUserRequest(user.getId()));

        // criminal minds ncis
        // cheifs packers
    }

    @Subscribe
    public void onEvents(GetEventsForUserResponse response) {
        games = response.getGames();
        episodes = response.getEpisodes();

        StickyListHeadersListView listView = (StickyListHeadersListView) getView().findViewById(R.id.events_list);

        adapter = new EventListAdapter(getActivity(), result.getTeams(), result.getShows());
        listView.setAdapter(adapter);
    }
}
