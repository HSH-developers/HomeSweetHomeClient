package com.example.hsh.homesweethome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.hsh.homesweethome.Models.Furniture;
import com.example.hsh.homesweethome.network.APIService;
import com.example.hsh.homesweethome.network.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView myrv ;
    RecyclerViewAdapterMain myAdapter;

    private String TAG  = "MainActivity";
    private APIService mAPIService = Utils.getAPIService();

    private List<Furniture> furnitures = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Call<List<Furniture>> call = mAPIService.getFurnitures();
        call.enqueue(new Callback<List<Furniture>>() {
            @Override
            public void onResponse(Call<List<Furniture>> call, Response<List<Furniture>> response) {
                if (response.isSuccessful()) {
                    furnitures.addAll(response.body());
                    myrv = findViewById(R.id.recyclerview_id);
                    myAdapter = new RecyclerViewAdapterMain(getApplicationContext(), furnitures);
                    myrv.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
                    myrv.setAdapter(myAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<Furniture>> call, Throwable t) {
                Log.e(TAG,t.getMessage());
            }
        });
    }
}
