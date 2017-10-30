package com.andro.indie.school.myretrofitapp.network;


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

    public void getAllCity(final MyCallback callback) {
        networkService.getCity().enqueue(new Callback<CityListResponse>() {
            @Override
            public void onResponse(Call<CityListResponse> call, Response<CityListResponse> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<CityListResponse> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    public interface MyCallback {
        void onSuccess(CityListResponse response);
        void onError(Throwable error);
    }
}
