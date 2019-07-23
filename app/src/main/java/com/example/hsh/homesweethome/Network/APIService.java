package com.example.hsh.homesweethome.Network;

import com.example.hsh.homesweethome.Model.Furniture;

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