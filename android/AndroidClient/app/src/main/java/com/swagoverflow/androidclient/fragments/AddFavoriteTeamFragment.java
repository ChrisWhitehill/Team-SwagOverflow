package com.swagoverflow.androidclient.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.swagoverflow.androidclient.R;
import com.swagoverflow.androidclient.adapters.AddOptionAdapter;
import com.swagoverflow.androidclient.api.ApiCallerProvider;
import com.swagoverflow.androidclient.api.IApiCaller;
import com.swagoverflow.androidclient.models.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFavoriteTeamFragment extends Fragment {

    private List<Team> teams;
    private IApiCaller apiCaller;

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
        apiCaller = ApiCallerProvider.getInstance();

        Spinner teamSelect = (Spinner) view.findViewById(R.id.teamSpinner);
        teamSelect.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.leagues)));
        teamSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // TODO change available teams
                String[] options = getResources().getStringArray(R.array.leagues);
                filterTeamsByLeague(options[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing
            }
        });
    }

    private void filterTeamsByLeague(String leagueName) {
        List<Team> filteredList = new ArrayList<>();
        for (Team t : teams) {
            if (t.getLeague().equals(leagueName)) {
                filteredList.add(t);
            }
        }

        ListView teamsList = (ListView) getView().findViewById(R.id.results);
        teamsList.setAdapter(new AddOptionAdapter(getActivity(), R.layout.layout_preference_item, filteredList, null));
    }
}
