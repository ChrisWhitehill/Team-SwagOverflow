package com.swagoverflow.androidclient;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.Switch;

public class ViewFavoriteActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_favorite);

        handleIntent(getIntent());
    }

    private void handleIntent(Intent intent) {
        boolean isTeam = intent.getBooleanExtra(Constants.IS_TEAM, false);
        boolean notifications = intent.getBooleanExtra(Constants.NOTIFICATIONS, true);

        Switch notificationsEnabled = (Switch) findViewById(R.id.notificationEnabled);
        notificationsEnabled.setChecked(notifications);

        if (isTeam) {
            // TODO get team info
        } else {
            // TODO get show info
        }
    }

}
