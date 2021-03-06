package com.andro.indie.school.myretrofitapp.network;

import com.andro.indie.school.myretrofitapp.network.response.CityListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by herisulistiyanto on 9/5/17.
 */

public interface NetworkService {

    @GET("v1/city")
    Call<CityListResponse> getCity(@Header("Authorization") String token);
}
