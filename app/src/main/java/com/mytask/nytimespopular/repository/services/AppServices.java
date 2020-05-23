package com.mytask.nytimespopular.repository.services;


import com.mytask.nytimespopular.model.ResultResponse;
import com.mytask.nytimespopular.model.baseresponse.GeneralResponse;
import com.mytask.nytimespopular.repository.WebAddressConstants;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class AppServices {

    private static AppServices instance;
    private static int timeOutDB = 30;
    private DataApi mDataApi;

    private AppServices() {

        /**
         * Logging
         */
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        /**
         * Add Interceptor for debug mode
         */

        interceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request.Builder ongoing = chain.request().newBuilder();
//            ongoing.addHeader("Authorization", "Bearer " + SessionManager.getUserToken());
            ongoing.addHeader("Accept", "application/json");
            return chain.proceed(ongoing.build());
        })

                .readTimeout(timeOutDB, TimeUnit.SECONDS)
                .connectTimeout(timeOutDB, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        Retrofit mRetrofit = new Retrofit.Builder().
                addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(WebAddressConstants.BASE_URL)
                .client(httpClient)
                .build();

        mDataApi = mRetrofit.create(DataApi.class);
    }

    public static AppServices getInstance() {
        if (instance == null) {
            instance = new AppServices();
        }
        return instance;
    }


    public DataApi getDataApi() {
        return mDataApi;
    }

    public interface DataApi {

        @GET(WebAddressConstants.ARTICLES)
        Flowable<Response<GeneralResponse<ResultResponse>>> getArticles(@Query("api-key") String key);

        // Main Categories API
//        @GET(WebAddressConstants.AppServices.GET_CATEGORIES)
//        Flowable<Response<GeneralResponse<List<MenuItems>>>> getCategories();

//        @GET(WebAddressConstants.ABOUT_US)
//        Single<Response<GeneralResponse<AboutUs>>> aboutUs();
//
//        @GET(WebAddressConstants.GET_MY_SERVICES)
//        Flowable<Response<GeneralResponse<ServiceResponse>>> getProviderServices();
    }
}


