package com.andro.indie.school.myretrofitapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.andro.indie.school.myretrofitapp.R;
import com.andro.indie.school.myretrofitapp.network.NetworkService;
import com.andro.indie.school.myretrofitapp.network.RestService;
import com.andro.indie.school.myretrofitapp.network.response.CityListResponse;
import com.andro.indie.school.myretrofitapp.ui.adapter.MyRecyclerAdapter;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    private Retrofit retrofit;
    private OkHttpClient okHttpClient;
    private HttpLoggingInterceptor httpLoggingInterceptor;
    private NetworkService networkService;
    private RestService restService;

    private RecyclerView recyclerView;
    private MyRecyclerAdapter adapter;

//    private int FAKE_CODE = 401;
//    private String fakeResponse;
//    private FakeInterceptor fakeInterceptor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rc_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerAdapter(this);
        recyclerView.setAdapter(adapter);

//        fakeInterceptor = new FakeInterceptor(FAKE_CODE, fakeResponse);

        initRestComponent();
        loadData();

    }

    private void initRestComponent() {

        httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
//                .addInterceptor(fakeInterceptor)  // Use this to intercept and create 401 exception
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://private-b8cf44-androidcleancode.apiary-mock.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        networkService = retrofit.create(NetworkService.class);

        restService = new RestService(networkService);

    }

    private void loadData() {
        restService.getAllCity(new RestService.MyCallback() {
            @Override
            public void onSuccess(CityListResponse response) {
                adapter.updateCityList(response.getData());
                Log.e(TAG, "onSuccess <<-------");
            }

            @Override
            public void onError(Throwable error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
