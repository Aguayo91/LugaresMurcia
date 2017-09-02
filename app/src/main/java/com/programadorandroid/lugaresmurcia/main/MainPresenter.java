package com.programadorandroid.lugaresmurcia.main;

import android.util.Log;

import com.programadorandroid.lugaresmurcia.R;
import com.programadorandroid.lugaresmurcia.data.Place;
import com.programadorandroid.lugaresmurcia.data.source.remote.PlacesRemoteDataSource;

import java.util.List;

/**
 * Created by Guillermo on 02/09/2017.
 */

class MainPresenter {

    private final static String LOG_TAG = "MainPresenter";

    private View view;
    private PlacesRemoteDataSource placesRemoteDataSource;

    MainPresenter(View view, PlacesRemoteDataSource placesRemoteDataSource) {
        this.view = view;
        this.placesRemoteDataSource = placesRemoteDataSource;
    }

    void loadPlaces(){
        Log.d(LOG_TAG,"loadPlaces");
        placesRemoteDataSource.loadPlaces(new PlacesRemoteDataSource.GetPlacesCallback() {
            @Override
            public void onPlacesLoaded(List<Place> places) {
                Log.d(LOG_TAG,"loadPlaces: onPlacesLoaded");
                view.showPlaces(places);
            }

            @Override
            public void onError() {
                Log.d(LOG_TAG,"loadPlaces: onError");
                view.showMessage(R.string.errorLoadingPlaces);
            }
        });
    }

    interface View{
        void showPlaces(List<Place> places);
        void showMessage(int messageStringResurceID);
    }
}
