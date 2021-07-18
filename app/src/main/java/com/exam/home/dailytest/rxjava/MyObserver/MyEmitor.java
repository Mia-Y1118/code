package com.exam.home.dailytest.rxjava.MyObserver;

/**
 * Created by Yangxy on 2021/6/13
 * description --
 */
public interface MyEmitor<T> {

    void onNext(T e);

    void onError(Throwable e);

    void onComplete(T e);

}
