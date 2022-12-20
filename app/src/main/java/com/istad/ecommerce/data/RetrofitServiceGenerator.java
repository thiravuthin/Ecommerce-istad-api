package com.istad.ecommerce.data;

/*
 *
 *
 */

import com.istad.ecommerce.utils.BasicInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceGenerator {

    private static String baseUrl = "https://cms.istad.co/api/";

    /*to tell client that we are going to use basic auth security*/
    private static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BASIC);

    /*Use API that has security*/
    private static OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(new BasicInterceptor());

    /**/
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpBuilder.build()) // for API has security
            .addConverterFactory(GsonConverterFactory.create());

    /**/
    private static Retrofit retrofit = builder.build();

    /****************** Create service **********************/
    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
