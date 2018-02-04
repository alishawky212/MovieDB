package com.example.ali.moviedb.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ali.moviedb.R;

public class MainActivity extends AppCompatActivity {


    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.movie_details_container)!=null){

            mTwoPane = true;

            if(savedInstanceState != null ){

//                MovieDetailFragment fragment = new MovieDetailFragment();
//                getFragmentManager().beginTransaction().
//                        replace(R.id.movie_details_container,fragment,
//                                getString(R.string.detail_fragment_key)).
//                        commit();
            }
        }else{
            mTwoPane = false;
        }
    }
}
