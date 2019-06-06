package com.example.hsh.homesweethome;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView myrv ;
    private RecyclerViewAdapterMain mAdapter;

    private CompositeDisposable disposable = new CompositeDisposable();

    private PublishSubject<String> publishSubject = PublishSubject.create();
    private String TAG  = "MainActivity";
    private APIService mAPIService;

    private List<Furniture> furnitures = new ArrayList<>();

    private Unbinder unbinder;

    private FloatingSearchView mSearchView;

//    @BindView(R.id.search_bar)
//    EditText searchBar;

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

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setElevation(4);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mSearchView = findViewById(R.id.search_bar);

        mSearchView.setOnLeftMenuClickListener(new FloatingSearchView.OnLeftMenuClickListener() {
            @Override
            public void onMenuOpened() {

            }

            @Override
            public void onMenuClosed() {

            }
        });

        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {

            }
        });

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

//        DisposableObserver<List<Furniture>> observer = getSearchObserver();
//
//        disposable.add(
//                publishSubject
//                        .debounce(300, TimeUnit.MILLISECONDS)
//                        .distinctUntilChanged()
//                        .switchMapSingle(new Function<String, Single<List<Furniture>>>() {
//                            @Override
//                            public Single<List<Furniture>> apply(String s) throws Exception {
//                                return mAPIService.getQueryFurnitures(20, s)
//                                        .subscribeOn(Schedulers.io())
//                                        .observeOn(AndroidSchedulers.mainThread());
//                            }
//                        })
//                        .subscribeWith(observer));
//
//        disposable.add(RxTextView.textChangeEvents(mSearchView)
//                .skipInitialValue()
//                .debounce(300, TimeUnit.MILLISECONDS)
//                /*.filter(new Predicate<TextViewTextChangeEvent>() {
//                    @Override
//                    public boolean test(TextViewTextChangeEvent textViewTextChangeEvent) throws Exception {
//                        return TextUtils.isEmpty(textViewTextChangeEvent.text().toString()) || textViewTextChangeEvent.text().toString().length() > 2;
//                    }
//                })*/
//                .distinctUntilChanged()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(searchContacts()));
//
//        disposable.add(observer);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
