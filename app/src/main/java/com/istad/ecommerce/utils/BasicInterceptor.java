package com.istad.ecommerce.utils;

/*basic interceptor use for config API security*/

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BasicInterceptor implements Interceptor {

    String credential;

    // we config by type security : Our API we use basic security
    public BasicInterceptor() {

        this.credential = Credentials.basic("mobile", "mobile123");
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Request authenticatedRequest = request.newBuilder()
                .addHeader("Authorization", credential).build();
        return chain.proceed(authenticatedRequest);
    }
}
