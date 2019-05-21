package com.example.hsh.homesweethome.network;

import com.example.hsh.homesweethome.Models.Furniture;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("/")
    Call<List<Furniture>> getFurnitures();


}