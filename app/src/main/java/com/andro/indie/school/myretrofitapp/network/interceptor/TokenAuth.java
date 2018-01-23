package com.andro.indie.school.myretrofitapp.network.interceptor;

import android.support.annotation.Nullable;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

/**
 * Created by herisulistiyanto on 11/7/17.
 */

public class TokenAuth implements Authenticator {

    private String token;

    public TokenAuth(String token) {
        this.token = token;
    }

    @Nullable
    @Override
    public Request authenticate(Route route, Response response) throws IOException {

        Request.Builder newRequestBuilder = response.request().newBuilder();
        newRequestBuilder.addHeader("Authorization", token);

        return newRequestBuilder.build();
    }
}
