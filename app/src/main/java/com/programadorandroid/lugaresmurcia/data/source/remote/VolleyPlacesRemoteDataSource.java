package com.programadorandroid.lugaresmurcia.data.source.remote;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.programadorandroid.lugaresmurcia.data.Place;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guillermo on 02/09/2017.
 */

public class VolleyPlacesRemoteDataSource implements PlacesRemoteDataSource{

    private static VolleyPlacesRemoteDataSource INSTANCE = null;

    private final static String PLACES_URL = "http://nexo.carm.es/nexo/archivos/recursos/opendata/json/Campings.json";
    private final static String LOG_TAG = "VolleyPlacesRemoteDS";
    private Context context;
    private RequestQueue requestQueue;

    private VolleyPlacesRemoteDataSource(Context context){
        this.context = context;
        // Instantiate the RequestQueue
        requestQueue = Volley.newRequestQueue(context);
    }

    public static VolleyPlacesRemoteDataSource getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = new VolleyPlacesRemoteDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void loadPlaces(final GetPlacesCallback callback) {

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                PLACES_URL,
                null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray responsePlaces) {
                        Log.d(LOG_TAG,responsePlaces.toString());
                        try {
                            List<Place> places = new ArrayList<>();
                            for (int i = 0;i<responsePlaces.length();i++) {
                                JSONObject placeJSON = responsePlaces.getJSONObject(i);
                                places.add(getPlaceFromJSON(placeJSON));
                            }

                            callback.onPlacesLoaded(places);
                        } catch (JSONException e) {
                            callback.onError();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(LOG_TAG,error.toString());
                        callback.onError();
                    }
                });

        requestQueue.add(request);
    }

    private Place getPlaceFromJSON(JSONObject placeJSON) throws JSONException {
        Place place = new Place();

        place.setName(placeJSON.getString("Nombre"));

        return place;
    }
}
