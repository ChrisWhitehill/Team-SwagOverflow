package com.swagoverflow.androidclient.api;

import com.squareup.otto.Bus;

public class BusProvider {
    private static Bus mBus;

    private BusProvider() {

    }

    public static Bus getInstance() {
        if (mBus == null) {
            mBus = new Bus();
        }

        return mBus;
    }
}
