package com.example.ali.moviedb.Views;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.ali.moviedb.Adapters.PosterAdapter;
import com.example.ali.moviedb.Contracts.PosterFragmentMVP;
import com.example.ali.moviedb.DI.Modules.PosterFragmentModule;
import com.example.ali.moviedb.Models.Movie;
import com.example.ali.moviedb.MyApplication;
import com.example.ali.moviedb.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ali on 2/4/2018.
 */

public class PosterFragment extends Fragment implements PosterFragmentMVP.View{

    @BindView(R.id.gridview_frag)
    GridView mGridView;

    @Inject
    PosterFragmentMVP.Presenter presenter;

    @Inject
    PosterAdapter adapter;

    private ArrayList<Movie> moviesssss;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyApplication.getInstance().getComponent().plus(new PosterFragmentModule(this,getActivity())).inject(this);

        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.movie_overview, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this,view);

        mGridView.setAdapter(adapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = (Movie) parent.getItemAtPosition(position);
                Bundle bundle = new Bundle();
            }
        });

        if (savedInstanceState != null) {
            moviesssss = savedInstanceState.getParcelableArrayList("movies");
            List<Movie> movieList = savedInstanceState.getParcelableArrayList("movies");
            adapter.updateData(movieList);
        } else {
            presenter.getMovies();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("movies",moviesssss);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.popl:
//                updateSharedPrefrence(getString(R.string.tmdb_sort_pop_desc));
//                getMoviesFromTMDb(getSortMethod());
                return true;

            case R.id.rate:
//                updateSharedPrefrence(getString(R.string.tmdb_sort_top_desc));
//                getMoviesFromTMDb(getSortMethod());
                return true;

            case R.id.favor:
//                updateSharedPrefrence(getString(R.string.favorite));
//                new FetchFavoriteMoviesTask(getActivity()).execute();


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showMovies(ArrayList<Movie> movies) {

        adapter.updateData(movies);
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(),"There Is Error Occurred",Toast.LENGTH_SHORT).show();

    }
}
