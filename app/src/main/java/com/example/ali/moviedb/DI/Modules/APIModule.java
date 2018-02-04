package com.example.ali.moviedb.DI.Modules;

import com.example.ali.moviedb.Contracts.APIServices;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ali on 2/4/2018.
 */
@Module
public class APIModule {

    public final String BASEURL = "https://api.themoviedb.org/";

    @Provides
    public OkHttpClient provideClinet (){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    @Provides
    public Retrofit provideRetrofit(String base_url, OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .baseUrl(base_url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    public APIServices.TMDbPopular provideTmDbPopular(){
       return provideRetrofit(BASEURL,provideClinet()).create(APIServices.TMDbPopular.class);
    }

    @Provides
    public APIServices.TMDbServiceTopRated provideTmDbServiceTopRated(){

        return provideRetrofit(BASEURL,provideClinet()).create(APIServices.TMDbServiceTopRated.class);
    }
}
