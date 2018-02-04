package com.example.ali.moviedb.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ali.moviedb.R;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){

            MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
            movieDetailFragment.setArguments(bundle);

            getFragmentManager().beginTransaction().add(R.id.detail_container,movieDetailFragment)
                    .commit();
        }
    }
}
