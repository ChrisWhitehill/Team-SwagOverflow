package com.swagoverflow.androidclient.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.swagoverflow.androidclient.R;
import com.swagoverflow.androidclient.models.Episode;
import com.swagoverflow.androidclient.models.Game;

import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by Mike on 2/27/2016.
 */
public class EventListAdapter extends BaseAdapter implements StickyListHeadersAdapter, SectionIndexer {

    private Activity context;
    private List<Game> games;
    private List<Episode> episodes;
    private String[] mSections = new String[] { "Games", "Episodes" };

    public EventListAdapter(Activity context, List<Game> games, List<Episode> episodes) {
        this.context = context;
        this.games = games;
        this.episodes = episodes;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;

        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.header, parent, false);
            holder.textView = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }

        if (position < games.size()) {
            holder.textView.setText(mSections[0]);
        } else {
            holder.textView.setText(mSections[1]);
        }

        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        if (position < games.size()) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int getCount() {
        return games.size() + episodes.size();
    }

    @Override
    public Object getItem(int i) {
        if (i < games.size()) {
            return games.get(i);
        } else if (i < episodes.size() + games.size()) {
            return episodes.get(i - games.size());
        }

        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_event, parent, false);

        ImageView thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView date = (TextView) view.findViewById(R.id.date);
        TextView channel = (TextView) view.findViewById(R.id.channel);

        if (position < games.size()) {
            Game game = games.get(position);
            Picasso.with(context).load(game.getThumbnailUrl()).into(thumbnail);
            name.setText(game.getDescription());
            date.setText(game.getDate().toString());
            channel.setText(game.getChannel());
        } else if (position < games.size() + episodes.size()) {
            Episode episode = episodes.get(position - games.size());
            Picasso.with(context).load(episode.getThumbnailUrl()).into(thumbnail);
            name.setText(episode.getShow().getName());
            date.setText(episode.getDate().toString());
            channel.setText(episode.getChannel());
        }

        return view;
    }

    @Override
    public Object[] getSections() {
        return mSections;
    }

    @Override
    public int getPositionForSection(int i) {
        if (games.size() == 0 && episodes.size() == 0) {
            return 0;
        }

        if (i <= 0) {
            return 0;
        } else {
            return games.size();
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
