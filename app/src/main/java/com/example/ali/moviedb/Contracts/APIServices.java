package com.example.ali.moviedb.Contracts;

import com.example.ali.moviedb.Models.MoviesWrapper;
import com.example.ali.moviedb.Models.ReviewsWrapper;
import com.example.ali.moviedb.Models.TrailersWrapper;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ali on 2/4/2018.
 */

public interface APIServices {

    interface TMDbPopular {
        @GET("/3/movie/popular")
        Single<MoviesWrapper> getMovies(
                @Query("api_key") String apiKey);
    }

    interface TMDbServiceTopRated {
        @GET("/3/movie/top_rated")
        Single<MoviesWrapper> getMovies(
                @Query("api_key") String apiKey);
    }
    interface TrailersService {
        @GET("/3/movie/{id}/videos")
        Call<TrailersWrapper> getTrailers(
                @Path("id") int id,
                @Query("api_key") String apiKey);
    }
    interface ReviewsService {
        @GET("/3/movie/{id}/reviews")
        Call<ReviewsWrapper> getReviews(
                @Path("id") int id,
                @Query("api_key") String apiKey);
    }
}
