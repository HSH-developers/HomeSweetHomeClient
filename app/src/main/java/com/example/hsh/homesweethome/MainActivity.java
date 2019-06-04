package com.example.hsh.homesweethome;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.example.hsh.homesweethome.Models.Furniture;
import com.example.hsh.homesweethome.network.APIService;
import com.example.hsh.homesweethome.network.Utils;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView myrv ;
    private RecyclerViewAdapterMain mAdapter;

    private CompositeDisposable disposable = new CompositeDisposable();

    private PublishSubject<String> publishSubject = PublishSubject.create();
    private String TAG  = "MainActivity";
    private APIService mAPIService;

    private List<Furniture> furnitures = new ArrayList<>();

    private Unbinder unbinder;

    @BindView(R.id.search_bar)
    EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);

        myrv = findViewById(R.id.recyclerview_id);
        mAdapter = new RecyclerViewAdapterMain(getApplicationContext(), furnitures);

        myrv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        myrv.setAdapter(mAdapter);

        mAPIService = Utils.getAPIService();
        Call<List<Furniture>> call = mAPIService.getFurnitures();
        call.enqueue(new Callback<List<Furniture>>() {
            @Override
            public void onResponse(Call<List<Furniture>> call, Response<List<Furniture>> response) {
                if (response.isSuccessful()) {
                    Log.e(TAG, response.body().toString());
                    furnitures.addAll(response.body());
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Furniture>> call, Throwable t) {
                Log.e(TAG, t.getMessage());

            }
        });

        DisposableObserver<List<Furniture>> observer = getSearchObserver();

        disposable.add(
                publishSubject
                        .debounce(300, TimeUnit.MILLISECONDS)
                        .distinctUntilChanged()
                        .switchMapSingle(new Function<String, Single<List<Furniture>>>() {
                            @Override
                            public Single<List<Furniture>> apply(String s) throws Exception {
                                return mAPIService.getQueryFurnitures(20, s)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread());
                            }
                        })
                        .subscribeWith(observer));

        disposable.add(RxTextView.textChangeEvents(searchBar)
                .skipInitialValue()
                .debounce(300, TimeUnit.MILLISECONDS)
                /*.filter(new Predicate<TextViewTextChangeEvent>() {
                    @Override
                    public boolean test(TextViewTextChangeEvent textViewTextChangeEvent) throws Exception {
                        return TextUtils.isEmpty(textViewTextChangeEvent.text().toString()) || textViewTextChangeEvent.text().toString().length() > 2;
                    }
                })*/
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(searchContacts()));

        disposable.add(observer);

    }



    private DisposableObserver<TextViewTextChangeEvent> searchContacts() {
        return new DisposableObserver<TextViewTextChangeEvent>() {
            @Override
            public void onNext(TextViewTextChangeEvent textViewTextChangeEvent) {
                Log.d(TAG, "Search query: " + textViewTextChangeEvent.text());
                mAdapter.getFilter().filter(textViewTextChangeEvent.text());
                publishSubject.onNext(textViewTextChangeEvent.text().toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
    }


    private DisposableObserver<List<Furniture>> getSearchObserver() {
        return new DisposableObserver<List<Furniture>>() {
            @Override
            public void onNext(List<Furniture> furnitures) {
                furnitures.addAll(furnitures);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
    }

}
