package com.programadorandroid.lugaresmurcia.data.source.remote;

import com.programadorandroid.lugaresmurcia.data.Place;

import java.util.List;

/**
 * Created by Guillermo on 02/09/2017.
 */

public interface PlacesRemoteDataSource {
    void loadPlaces(GetPlacesCallback callback);

    public interface GetPlacesCallback{
        void onPlacesLoaded(List<Place> places);
        void onError();
    }
}
