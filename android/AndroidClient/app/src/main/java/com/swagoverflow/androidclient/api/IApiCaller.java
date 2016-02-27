package com.swagoverflow.androidclient.api;

/**
 * This is a base level for our api caller. It contains the methods needed by any api caller
 * that is to be used with the project.
 */
public interface IApiCaller {
    void obtainData(Object object);
    void registerObject(Object object);
    void unregisterObject(Object object);
}
