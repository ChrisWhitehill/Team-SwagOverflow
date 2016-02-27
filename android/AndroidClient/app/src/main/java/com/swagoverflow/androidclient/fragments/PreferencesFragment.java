package com.swagoverflow.androidclient.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;
import com.swagoverflow.androidclient.R;
import com.swagoverflow.androidclient.SwagApplication;
import com.swagoverflow.androidclient.adapters.PreferenceListAdapter;
import com.swagoverflow.androidclient.api.ApiCallerProvider;
import com.swagoverflow.androidclient.api.IApiCaller;
import com.swagoverflow.androidclient.api.requests.ObtainFavoritesRequest;
import com.swagoverflow.androidclient.api.responses.ObtainFavoritesResult;
import com.swagoverflow.androidclient.models.User;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class PreferencesFragment extends Fragment {

    private static final String TAG = "PreferencesFragment";

    private IApiCaller apiCaller;

    public PreferencesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_preferences, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        User user = ((SwagApplication) getActivity().getApplication()).getUser();
        apiCaller = ApiCallerProvider.getInstance();
        apiCaller.obtainData(new ObtainFavoritesRequest(user.getId()));
    }

    @Override
    public void onPause() {
        apiCaller.unregisterObject(this);

        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        apiCaller.registerObject(this);
    }

    @Subscribe
    public void onFavoritesObtained(ObtainFavoritesResult result) {
        StickyListHeadersListView listView = (StickyListHeadersListView) getView().findViewById(R.id.preference_list);
        listView.setAdapter(new PreferenceListAdapter(getActivity(), result.getTeams(), result.getShows()));

        User user = ((SwagApplication) getActivity().getApplication()).getUser();
        user.setFavoriteShows(result.getShows());
        user.setFavoriteTeams(result.getTeams());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getActivity(), "Todo", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
