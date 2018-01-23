package com.andro.indie.school.myretrofitapp.network;

import android.support.annotation.NonNull;

import com.andro.indie.school.myretrofitapp.network.response.CityListResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by herisulistiyanto on 9/5/17.
 */

public class RestService {

    private NetworkService networkService;

    public RestService(NetworkService networkService) {
        this.networkService = networkService;
    }

    public void getAllCity(final String token, final MyCallback callback) {
        networkService.getCity(token).enqueue(new Callback<CityListResponse>() {
            @Override
            public void onResponse(@NonNull Call<CityListResponse> call, @NonNull Response<CityListResponse> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<CityListResponse> call, @NonNull Throwable t) {
                callback.onError(t);
            }
        });
    }

    public interface MyCallback {
        void onSuccess(CityListResponse response);
        void onError(Throwable error);
    }
}
