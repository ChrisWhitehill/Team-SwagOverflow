package com.swagoverflow.androidclient.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.swagoverflow.androidclient.R;
import com.swagoverflow.androidclient.api.ApiCallerProvider;
import com.swagoverflow.androidclient.api.IApiCaller;
import com.swagoverflow.androidclient.api.requests.ObtainFavoritesRequest;

public class PreferencesFragment extends ListFragment {

    private ListView preferencesListView;
    private IApiCaller apiCaller;

    public PreferencesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        apiCaller = ApiCallerProvider.getInstance();
        apiCaller.obtainData(new ObtainFavoritesRequest());
    }
}
