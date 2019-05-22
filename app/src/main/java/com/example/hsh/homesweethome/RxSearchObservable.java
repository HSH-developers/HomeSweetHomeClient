package com.example.hsh.homesweethome;

import com.arlib.floatingsearchview.FloatingSearchView;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxSearchObservable {

    public static Observable<String> fromView(FloatingSearchView searchView) {

        final PublishSubject<String> subject = PublishSubject.create();

        searchView.setOnQueryChangeListener((oldQuery, newQuery) -> {
            subject.onNext(newQuery);

        });

        return subject;
    }
}