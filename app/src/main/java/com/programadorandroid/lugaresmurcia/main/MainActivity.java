package com.programadorandroid.lugaresmurcia.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.programadorandroid.lugaresmurcia.R;
import com.programadorandroid.lugaresmurcia.data.Place;
import com.programadorandroid.lugaresmurcia.data.source.remote.PlacesRemoteDataSource;
import com.programadorandroid.lugaresmurcia.data.source.remote.VolleyPlacesRemoteDataSource;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainPresenter.View{

    private MainPresenter presenter;
    private TextView contentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Views
        contentTextView = (TextView) findViewById(R.id.contentTextView);

        //Presenter
        presenter = new MainPresenter(this, VolleyPlacesRemoteDataSource.getInstance(this));

        //Setup activity
        presenter.loadPlaces();

    }

    @Override
    public void showPlaces(List<Place> places) {
        StringBuilder stringBuilderPlaces = new StringBuilder();
        for (Place place : places) {
            stringBuilderPlaces.append(place.getName());
            stringBuilderPlaces.append(",");
        }

        contentTextView.setText(stringBuilderPlaces.toString());
    }

    @Override
    public void showMessage(int messageStringResurceID) {

    }
}
