package com.swagoverflow.androidclient.utilities;

import android.util.DisplayMetrics;
import android.view.Display;

/**
 * Created by Mike on 2/27/2016.
 */
public final class Utility {
    private Utility() { }

    public static int convertDpToPx(Display display, int dp) {
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        float logicalDensity = metrics.density;

        return (int) Math.ceil(dp * logicalDensity);
    }
}
