package com.andro.indie.school.myretrofitapp.network.interceptor;

import android.support.annotation.NonNull;

import com.andro.indie.school.myretrofitapp.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by herisulistiyanto on 9/19/17.
 */

public class FakeInterceptor implements Interceptor {

    private final static String TAG = FakeInterceptor.class.getSimpleName();

    private final int responseCode;
    private final String responseString;

    private final static String FAKE_FAIL = "{\"status\":401\"}";

    public FakeInterceptor(int responseCode, String responseString) {
        this.responseCode = responseCode;
        this.responseString = FAKE_FAIL;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Response response;

        if(BuildConfig.DEBUG) {

            response = new Response.Builder()
                    .code(responseCode)
                    .message(responseString)
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_0)
                    .addHeader("content-type", "application/json")
                    .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()))
                    .build();

        } else {
            response = chain.proceed(chain.request());
        }

        return response;
    }

}
