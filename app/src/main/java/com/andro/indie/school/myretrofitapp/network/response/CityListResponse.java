package com.andro.indie.school.myretrofitapp.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CityListResponse {

    @SerializedName("data")
    private List<City> data = new ArrayList<>();
    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private int status;

    public List<City> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public static class City {

        @SerializedName("id")
        private String id;

        @SerializedName("name")
        private String name;

        @SerializedName("description")
        private String description;

        @SerializedName("background")
        private String background;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getBackground() {
            return background;
        }
    }

}