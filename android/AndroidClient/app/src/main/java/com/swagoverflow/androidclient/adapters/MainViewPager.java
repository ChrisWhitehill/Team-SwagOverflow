package com.swagoverflow.androidclient.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.swagoverflow.androidclient.fragments.EventsFragment;
import com.swagoverflow.androidclient.fragments.PreferencesFragment;
import com.swagoverflow.androidclient.fragments.SettingsFragment;

public class MainViewPager extends FragmentPagerAdapter {
    public MainViewPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;

        switch (position) {
            case 0:
                fragment = new PreferencesFragment();
                break;
            case 1:
                fragment = new EventsFragment();
                break;
            default:
                fragment = new SettingsFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }
}
