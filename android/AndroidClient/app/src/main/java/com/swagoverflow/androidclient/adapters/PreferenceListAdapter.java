package com.swagoverflow.androidclient.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.swagoverflow.androidclient.R;
import com.swagoverflow.androidclient.SwagApplication;
import com.swagoverflow.androidclient.api.IApiCaller;
import com.swagoverflow.androidclient.api.requests.DeleteFavoriteShowRequest;
import com.swagoverflow.androidclient.api.requests.DeleteFavoriteTeamRequest;
import com.swagoverflow.androidclient.models.Show;
import com.swagoverflow.androidclient.models.ShowFavorite;
import com.swagoverflow.androidclient.models.Team;
import com.swagoverflow.androidclient.models.TeamFavorite;
import com.swagoverflow.androidclient.models.User;
import com.swagoverflow.androidclient.utilities.Utility;

import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

public class PreferenceListAdapter extends BaseAdapter implements StickyListHeadersAdapter, SectionIndexer {

    private List<TeamFavorite> teams;
    private List<ShowFavorite> shows;
    private Activity activity;
    private String[] mSections;
    private IApiCaller apiCaller;

    public PreferenceListAdapter(Activity activity, List<TeamFavorite> teams, List<ShowFavorite> shows, IApiCaller apiCaller) {
        this.teams = teams;
        this.shows = shows;
        this.activity = activity;
        this.apiCaller = apiCaller;

        mSections = new String[] { "Teams", "Shows" };
    }

    @Override
    public int getCount() {
        return teams.size() + shows.size() + 1;
    }

    @Override
    public Object getItem(int i) {
        if (i < teams.size()) {
            return teams.get(i);
        } else if (i < teams.size() + shows.size()) {
            return shows.get(i - teams.size());
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(activity).inflate(R.layout.layout_preference_item, parent, false);

        ImageView logo = (ImageView) view.findViewById(R.id.logo);
        TextView name = (TextView) view.findViewById(R.id.name);
        ImageView delete = (ImageView) view.findViewById(R.id.delete);

        final User user = ((SwagApplication) activity.getApplication()).getUser();

        if (position < teams.size()) {
            final Team team = teams.get(position).getTeam();
            Picasso.with(activity).load(team.getImageUrl()).into(logo);
            name.setText(team.getName());
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    apiCaller.obtainData(new DeleteFavoriteTeamRequest(user.getId(), teams.get(position).getId()));
                }
            });
        } else if (position < teams.size() + shows.size()) {
            final Show show = shows.get(position - teams.size()).getShow();
            Picasso.with(activity).load(show.getImageUrl()).into(logo);
            name.setText(show.getName());
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    apiCaller.obtainData(new DeleteFavoriteShowRequest(user.getId(), shows.get(position  - teams.size()).getId()));
                }
            });
        } else {
            view.setMinimumHeight(Utility.convertDpToPx(activity.getWindowManager().getDefaultDisplay(), 110));
            delete.setVisibility(View.GONE);
            name.setVisibility(View.GONE);
        }

        return view;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;

        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = LayoutInflater.from(activity).inflate(R.layout.header, parent, false);
            holder.textView = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }

        if (position < teams.size()) {
            holder.textView.setText(mSections[0]);
        } else {
            holder.textView.setText(mSections[1]);
        }

        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        if (position < teams.size()) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public Object[] getSections() {
        return mSections;
    }

    @Override
    public int getPositionForSection(int i) {
        if (teams.size() == 0 && shows.size() == 0) {
            return 0;
        }

        if (i <= 0) {
            return 0;
        } else {
            return teams.size();
        }
    }

    @Override
    public int getSectionForPosition(int i) {
        return 0;
    }

    class HeaderViewHolder {
        TextView textView;
    }
}
