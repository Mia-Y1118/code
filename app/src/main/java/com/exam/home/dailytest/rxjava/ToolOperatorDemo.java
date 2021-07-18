package com.exam.home.dailytest.rxjava;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Yangxy on 2021/6/3
 * description -- 功能操作符
 * <p>
 * subscribeOn(): 决定subscribe 执行的线程，也就是产生事件发射事件的所在线程
 */
public class ToolOperatorDemo {
    public static void main(String[] args) {
        ToolOperatorDemo demo = new ToolOperatorDemo();
        demo.test1();
    }

    private void test1() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                System.out.println("subscribe = " + Thread.currentThread());
                Thread.sleep(2000);
                emitter.onNext("aaaaaa");
                emitter.onNext("bbbbbb");
                emitter.onComplete();

            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())//决定下游处理事件的线程
                .map(new Function<Object, Object>() {
                    @Override
                    public Object apply(Object o) throws Exception {
                        System.out.println("apply = " + o + Thread.currentThread());
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io()) //线程池
                .subscribe(new Observer<Object>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("d = " + d + Thread.currentThread());
                    }

                    @Override
                    public void onNext(Object o) {
                        System.out.println("o = " + o + Thread.currentThread());

                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("e = " + e + Thread.currentThread());

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete" + Thread.currentThread());

                    }
                });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
