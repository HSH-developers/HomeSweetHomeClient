package com.example.hsh.homesweethome.network;

import com.example.hsh.homesweethome.Models.Furniture;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIService {

    @GET("/internal/v1/furniture/list")
    Call<List<Furniture>> getFurnitures();

}