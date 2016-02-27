package com.swagoverflow.androidclient.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.swagoverflow.androidclient.fragments.AddFavoriteShowFragment;
import com.swagoverflow.androidclient.fragments.AddFavoriteTeamFragment;

public class AddFavoritesAdapter extends FragmentPagerAdapter {
    public AddFavoritesAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;

        switch (position) {
            case 0:
                fragment = new AddFavoriteTeamFragment();
                break;
            default:
                fragment = new AddFavoriteShowFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return position == 0 ? "Add Team" : "Add Show";
    }
}
