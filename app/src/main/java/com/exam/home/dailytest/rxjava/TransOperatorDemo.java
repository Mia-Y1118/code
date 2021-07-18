package com.exam.home.dailytest.rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * Created by Yangxy on 2021/5/29
 * description -- 转换操作符
 * <p>
 * Map:对发射出来的事件进行处理，并且产生新的事件再发射(对被观察者的事件进行处理）
 *
 * FlatMap:接收观察者的事件，再生成新的被观察者.对事件序列中的元素进行整合加工，返回一个新的被观察者
 *
 * ConcatMap：与FlatMap基本是一致的，concatMap转发出来的是一个有序的事件。
 */
public class TransOperatorDemo {
    public static void main(String[] args) {
        System.out.println("______________________________________________________________");
        TransOperatorDemo transOperatorDemo = new TransOperatorDemo();
        transOperatorDemo.test1();
        System.out.println("______________________________________________________________");
        transOperatorDemo.test2();
        System.out.println("______________________________________________________________");
        transOperatorDemo.test3();
        System.out.println("______________________________________________________________");
        transOperatorDemo.test4();
    }

    Observer observable = new Observer<Object>() {
        @Override
        public void onSubscribe(Disposable d) {
            System.out.println("onSubscribe = " + d);
        }

        @Override
        public void onNext(Object o) {
            System.out.println("onNext = " + o);
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("onError = " + e);

        }

        @Override
        public void onComplete() {
            System.out.println("onComplete");
        }
    };

    private void test1() {
        Observable.just("map")
                .map(new Function<String, Object>() {
                    @Override
                    public Object apply(String s) throws Exception {
                        return "transMap";
                    }
                }).subscribe(observable);
    }

    private void test2(){
        //网络嵌套的时候常用的操作符
        Observable.just("flatMap")
                .flatMap(new Function<String, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(String s) throws Exception {

                        return Observable.just("Observable : FlatMap  " + s);
                    }
                })
                .subscribe(observable);
    }

    private void test3(){
        //网络嵌套的时候常用的操作符
        Observable.just("111","222","333")
                .concatMap(new Function<String, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(String s) throws Exception {

                        return Observable.just("Observable : FlatMap  " + s);
                    }
                })
                .subscribe(observable);
    }

    private void test4(){
        //网络嵌套的时候常用的操作符
        Observable.just("111","222","333","444","555","666","7")
                .buffer(3)
                .subscribe(observable);
    }





}
