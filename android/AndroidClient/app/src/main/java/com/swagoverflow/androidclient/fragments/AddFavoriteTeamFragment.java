package com.swagoverflow.androidclient.fragments;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.squareup.otto.Subscribe;
import com.swagoverflow.androidclient.R;
import com.swagoverflow.androidclient.SwagApplication;
import com.swagoverflow.androidclient.adapters.AddOptionAdapter;
import com.swagoverflow.androidclient.api.ApiCallerProvider;
import com.swagoverflow.androidclient.api.IApiCaller;
import com.swagoverflow.androidclient.api.requests.DeleteFavoriteTeamRequest;
import com.swagoverflow.androidclient.api.requests.GetTeamsRequest;
import com.swagoverflow.androidclient.api.requests.PostFavoriteTeamRequest;
import com.swagoverflow.androidclient.api.responses.GetTeamsResponse;
import com.swagoverflow.androidclient.api.responses.TeamPostedResponse;
import com.swagoverflow.androidclient.models.Team;
import com.swagoverflow.androidclient.models.TeamFavorite;
import com.swagoverflow.androidclient.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFavoriteTeamFragment extends Fragment {

    private List<Team> teams;
    private List<Team> filteredTeams;
    private IApiCaller apiCaller;
    private long userId;
    private long teamId;
    private boolean shouldDelete;

    public AddFavoriteTeamFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_favorite_team, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        teams = new ArrayList<>();
        filteredTeams = new ArrayList<>();
        apiCaller = ApiCallerProvider.getInstance();
        apiCaller.obtainData(new GetTeamsRequest());

        Spinner teamSelect = (Spinner) view.findViewById(R.id.teamSpinner);
        teamSelect.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.leagues)));
        teamSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] options = getResources().getStringArray(R.array.leagues);
                filterTeamsByLeague(options[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing
            }
        });

        ListView teams = (ListView) view.findViewById(R.id.results);
        teams.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Team team = filteredTeams.get(i);
                User user = ((SwagApplication) getActivity().getApplication()).getUser();
                userId = user.getId();
                teamId = team.getId();
                apiCaller.obtainData(new PostFavoriteTeamRequest(team.getId(), user.getId()));
                CoordinatorLayout layout = (CoordinatorLayout) getActivity().findViewById(R.id.main_content);
                Snackbar.make(layout, "Favorite team added", Snackbar.LENGTH_SHORT)
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
    public void onPosted(TeamPostedResponse response) {
        ((SwagApplication) getActivity().getApplication()).getUser().addFavoriteTeam(response.getFavoriteteams());

        if (shouldDelete) {
            apiCaller.obtainData(new DeleteFavoriteTeamRequest(teamId, userId));
            CoordinatorLayout layout = (CoordinatorLayout) getActivity().findViewById(R.id.main_content);
            Snackbar.make(layout, "Successfully deleted team", Snackbar.LENGTH_SHORT)
                    .show();
        }
        shouldDelete = false;

        Spinner spinner = (Spinner) getView().findViewById(R.id.teamSpinner);
        int position = spinner.getSelectedItemPosition();
        String[] options = getResources().getStringArray(R.array.leagues);
        filterTeamsByLeague(options[position]);
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
    public void onTeamsObtained(GetTeamsResponse response) {
        this.teams = response.getTeams();

        Spinner spinner = (Spinner) getView().findViewById(R.id.teamSpinner);
        int position = spinner.getSelectedItemPosition();
        String[] options = getResources().getStringArray(R.array.leagues);

        filterTeamsByLeague(options[position]);
    }

    private void filterTeamsByLeague(String leagueName) {
        filteredTeams = new ArrayList<>();
        User user = ((SwagApplication) getActivity().getApplication()).getUser();
        List<TeamFavorite> teamFavorites = user.getFavoriteTeams();

        boolean hasTeam;

        if (!leagueName.equals("None")) {
            for (Team t : teams) {
                hasTeam = false;
                for (TeamFavorite f : teamFavorites) {
                    if (f.getTeam().getName().equals(t.getName())) {
                        hasTeam = true;
                        break;
                    }
                }

                if (!hasTeam && t.getLeague().equals(leagueName)){
                    filteredTeams.add(t);
                }
            }
        } else {
            for (Team t : teams) {
                hasTeam = false;
                for (TeamFavorite f : teamFavorites) {
                    if (f.getTeam().getName().equals(t.getName())) {
                        hasTeam = true;
                        break;
                    }
                }

                if (!hasTeam){
                    filteredTeams.add(t);
                }
            }
        }

        ListView teamsList = (ListView) getView().findViewById(R.id.results);
        teamsList.setAdapter(new AddOptionAdapter(getActivity(), R.layout.layout_preference_item, filteredTeams, null));
    }
}
