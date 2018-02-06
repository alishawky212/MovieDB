package com.example.ali.moviedb.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ali.moviedb.Contracts.OnPosterSelectedListener;
import com.example.ali.moviedb.R;

public class MainActivity extends AppCompatActivity implements OnPosterSelectedListener {


    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.movie_details_container)!=null){

            mTwoPane = true;

            if(savedInstanceState != null ){

                MovieDetailFragment fragment = new MovieDetailFragment();
                getFragmentManager().beginTransaction().
                        replace(R.id.movie_details_container, fragment,
                                getString(R.string.detail_fragment_key)).
                        commit();
            }
        }else{
            mTwoPane = false;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPosterSelected(Bundle bundle) {
        if (mTwoPane) {
            if (bundle != null) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.movie_details_container, createFragment(bundle),
                                getString(R.string.detail_fragment_key))
                        .commit();
            }
        } else {
            startActivity(createIntent(bundle));
        }
    }

    private MovieDetailFragment createFragment(Bundle bundle) {
        MovieDetailFragment fragment = new MovieDetailFragment();

        if (bundle != null && !bundle.isEmpty())
            fragment.setArguments(bundle);

        return fragment;
    }

    private Intent createIntent(Bundle bundle) {
        Intent intent = new Intent(this, MovieDetailActivity.class);

        if (bundle != null && !bundle.isEmpty())
            intent.putExtras(bundle);

        return intent;
    }

}
