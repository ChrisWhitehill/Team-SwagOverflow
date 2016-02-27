package com.swagoverflow.androidclient.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.swagoverflow.androidclient.R;
import com.swagoverflow.androidclient.models.Show;
import com.swagoverflow.androidclient.models.Team;

import java.util.List;

/**
 * Created by Mike on 2/27/2016.
 */
public class AddOptionAdapter extends ArrayAdapter<Team> {
    private List<Show> shows;
    private List<Team> teams;
    private Context context;

    public AddOptionAdapter(Context context, int resource, List<Team> teams, List<Show> shows) {
        super(context, resource);

        this.context = context;
        this.shows = shows;
        this.teams = teams;
    }

    @Override
    public int getCount() {
        if (teams != null) {
            return teams.size();
        } else {
            return shows.size();
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.layout_preference_item, parent, false);
        }

        TextView name = (TextView) view.findViewById(R.id.name);
        ImageView logo = (ImageView) view.findViewById(R.id.logo);
        ImageView delete = (ImageView) view.findViewById(R.id.delete);
        delete.setVisibility(View.GONE);

        if (teams != null) {
            Team team = teams.get(position);
            Picasso.with(getContext()).load(team.getImageUrl()).into(logo);
            name.setText(team.getName());
        } else {
            Show show = shows.get(position);
            Picasso.with(getContext()).load(show.getImageUrl()).into(logo);
            name.setText(show.getName());
        }

        return view;
    }
}
