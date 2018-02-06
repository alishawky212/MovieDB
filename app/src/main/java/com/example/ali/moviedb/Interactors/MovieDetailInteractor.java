package com.example.ali.moviedb.Interactors;

import com.example.ali.moviedb.Contracts.APIServices;
import com.example.ali.moviedb.Contracts.MovieDetailContracts;
import com.example.ali.moviedb.Models.TrailersWrapper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ali on 2/6/2018.
 */

public class MovieDetailInteractor implements MovieDetailContracts.MovieDetailInteractor {


    APIServices.TrailersService trailersService;
    APIServices.ReviewsService reviewsService;

    public MovieDetailInteractor(APIServices.TrailersService trailersService, APIServices.ReviewsService reviewsService) {
        this.trailersService = trailersService;
        this.reviewsService = reviewsService;
    }

    @Override
    public void getTrailers(int MovieId, String API, OnLoadTrailersFinishedListener listener) {

        Call<TrailersWrapper> call = trailersService.getTrailers(MovieId, API);

        call.enqueue(new Callback<TrailersWrapper>() {
            @Override
            public void onResponse(Call<TrailersWrapper> call, Response<TrailersWrapper> response) {

            }

            @Override
            public void onFailure(Call<TrailersWrapper> call, Throwable t) {

            }
        });
    }

    @Override
    public void getReviews(int MovieId, String API, OnLoadReviewsFinishedListener listener) {

    }
}
