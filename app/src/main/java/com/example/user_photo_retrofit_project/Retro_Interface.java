package com.example.user_photo_retrofit_project;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Retro_Interface {
    @GET("photos")
    Call<List<ModelClass>>showProduct();
}
