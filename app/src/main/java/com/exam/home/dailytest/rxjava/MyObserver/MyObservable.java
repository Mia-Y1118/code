package com.exam.home.dailytest.rxjava.MyObserver;

/**
 * Created by Yangxy on 2021/6/13
 * description -- 被观察者的核心类
 *
 * 也是使用框架的入口
 */
public abstract class MyObservable implements ObservableSource {


    @Override
    public void subscribe(MyObserver myObservable) {
        subscribeActual(myObservable);
    }

    protected abstract void subscribeActual(MyObserver myObservable);

    public static <T> MyObservable create(ObservableOnSubcribe observableOnSubcribe) {
        return new MyCreateObservable<T>(observableOnSubcribe);
    }

}
