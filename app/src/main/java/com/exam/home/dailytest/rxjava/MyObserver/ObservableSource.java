package com.exam.home.dailytest.rxjava.MyObserver;

/**
 * Created by Yangxy on 2021/6/13
 * description -- 被观察者的顶层接口
 */
public interface ObservableSource<T> {


    void subscribe(MyObserver myObservable);
}
