package com.example.hsh.homesweethome.network;

import com.example.hsh.homesweethome.Models.Furniture;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIService {

    @GET("/api/furnitures/retrieveAllFurnitures")
    Call<List<Furniture>> getFurnitures();

    @GET("/api/furnitures/retrieveQueryFurnitures/{limit}/{query}")
    Single<List<Furniture>> getQueryFurnitures(@Path(value = "limit", encoded = true) Integer limit, @Path(value = "query", encoded = true) String query);
}