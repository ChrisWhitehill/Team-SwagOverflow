package com.swagoverflow.androidclient.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Subscribe;
import com.swagoverflow.androidclient.R;
import com.swagoverflow.androidclient.api.ApiCallerProvider;
import com.swagoverflow.androidclient.api.IApiCaller;
import com.swagoverflow.androidclient.api.requests.GetShowsRequest;
import com.swagoverflow.androidclient.api.responses.GetShowsResponse;
import com.swagoverflow.androidclient.models.Show;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFavoriteShowFragment extends Fragment {

    private List<Show> shows;
    private List<Show> filteredShows;
    private IApiCaller apiCaller;

    public AddFavoriteShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_favorite_show, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        shows = new ArrayList<>();
        filteredShows = new ArrayList<>();
        apiCaller = ApiCallerProvider.getInstance();

        apiCaller.obtainData(new GetShowsRequest());
    }

    @Override
    public void onResume() {
        super.onResume();
        apiCaller.registerObject(this);
    }

    @Override
    public void onPause() {
        apiCaller.unregisterObject(this);
        super.onPause();
    }

    @Subscribe
    public void onShowsObtained(GetShowsResponse response) {
        this.shows = response.getShows();

        filterShows();
    }

    private void filterShows() {
        filteredShows = new ArrayList<>();
    }
}
