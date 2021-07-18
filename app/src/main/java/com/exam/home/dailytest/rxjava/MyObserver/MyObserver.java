package com.exam.home.dailytest.rxjava.MyObserver;

/**
 * Created by Yangxy on 2021/6/13
 * description --
 */
public interface MyObserver<T> {

    void onSubscribe();

    void onNext(T t);

    void onError(Throwable r);

    void onComplete(T t);

}
