package com.swagoverflow.androidclient.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.squareup.otto.Subscribe;
import com.swagoverflow.androidclient.R;
import com.swagoverflow.androidclient.SwagApplication;
import com.swagoverflow.androidclient.adapters.AddOptionAdapter;
import com.swagoverflow.androidclient.api.ApiCallerProvider;
import com.swagoverflow.androidclient.api.IApiCaller;
import com.swagoverflow.androidclient.api.requests.DeleteFavoriteShowRequest;
import com.swagoverflow.androidclient.api.requests.GetShowsRequest;
import com.swagoverflow.androidclient.api.requests.PostFavoriteShowRequest;
import com.swagoverflow.androidclient.api.responses.GetShowsResponse;
import com.swagoverflow.androidclient.api.responses.ShowPostedResponse;
import com.swagoverflow.androidclient.models.Show;
import com.swagoverflow.androidclient.models.ShowFavorite;
import com.swagoverflow.androidclient.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFavoriteShowFragment extends Fragment {

    private List<Show> shows;
    private List<Show> filteredShows;
    private IApiCaller apiCaller;
    private FilterShowsTask task;
    private boolean shouldDelete;
    private long userId;

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
        apiCaller = ApiCallerProvider.getInstance();
        task = null;

        EditText search = (EditText) getView().findViewById(R.id.searchField);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                User user = ((SwagApplication) getActivity().getApplication()).getUser();
                if (task != null) {
                    task.cancel(false);
                }
                task = new FilterShowsTask();
                task.execute(new Pair<>(editable.toString(), user));
            }
        });

        apiCaller.obtainData(new GetShowsRequest());

        ListView shows = (ListView) view.findViewById(R.id.results);
        shows.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Show show = filteredShows.get(i);
                User user = ((SwagApplication) getActivity().getApplication()).getUser();
                userId = user.getId();
                apiCaller.obtainData(new PostFavoriteShowRequest(show.getId(), user.getId()));
                CoordinatorLayout layout = (CoordinatorLayout) getActivity().findViewById(R.id.main_content);
                Snackbar.make(layout, "Favorite show added", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                shouldDelete = true;
                            }
                        })
                        .show();
            }
        });
    }

    @Subscribe
    public void onPosted(ShowPostedResponse response) {
        ((SwagApplication) getActivity().getApplication()).getUser().addFavoriteShow(response.getFavoriteshows());
        if (shouldDelete) {
            apiCaller.obtainData(new DeleteFavoriteShowRequest(userId, response.getFavoriteshows().getId()));
            CoordinatorLayout layout = (CoordinatorLayout) getActivity().findViewById(R.id.main_content);
            Snackbar.make(layout, "Successfully deleted show", Snackbar.LENGTH_SHORT)
                    .show();
        }
        shouldDelete = false;

        if (task != null) {
            task.cancel(false);
        }

        EditText search = (EditText) getView().findViewById(R.id.searchField);
        User user = ((SwagApplication) getActivity().getApplication()).getUser();

        task = new FilterShowsTask();
        task.execute(new Pair<>(search.getText().toString(), user));
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

        EditText search = (EditText) getView().findViewById(R.id.searchField);
        User user = ((SwagApplication) getActivity().getApplication()).getUser();

        if (task != null) {
            task.cancel(false);
        }
        task = new FilterShowsTask();
        task.execute(new Pair<>(search.getText().toString(), user));
    }

    private void filterShows(List<Show> filteredShows) {
        this.filteredShows = filteredShows;

        ListView showsList = (ListView) getView().findViewById(R.id.results);
        showsList.setAdapter(new AddOptionAdapter(getActivity(), R.layout.layout_preference_item, null, filteredShows));
    }

    private class FilterShowsTask extends AsyncTask<Pair<String, User>, Void, List<Show>> {

        @Override
        protected List<Show> doInBackground(Pair<String, User>... args) {
            List<Show> filteredShows = new ArrayList<>();
            boolean hasShow;
            Pair<String, User> data = args[0];
            String text = data.first.toLowerCase();
            List<ShowFavorite> showFavorites = data.second.getFavoriteShows();

            if (!text.equals("")) {
                for (Show s : shows) {
                    if (isCancelled())
                        break;
                    hasShow = false;
                    for (ShowFavorite f : showFavorites) {
                        if (f != null && f.getShow().getName().equals(s.getName())) {
                            hasShow = true;
                            break;
                        }
                        if (isCancelled())
                            break;
                    }

                    if (!hasShow && s.getName().toLowerCase().contains(text)){
                        filteredShows.add(s);
                    }
                }
            } else {
                for (Show s : shows) {
                    hasShow = false;
                    for (ShowFavorite f : showFavorites) {
                        if (f != null && f.getShow().getName().equals(s.getName())) {
                            hasShow = true;
                            break;
                        }
                    }

                    if (!hasShow){
                        filteredShows.add(s);
                    }
                }
            }

            return filteredShows;
        }

        @Override
        protected void onPostExecute(List<Show> shows) {
            filterShows(shows);
        }
    }
}
