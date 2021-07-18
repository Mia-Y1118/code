package com.exam.home.dailytest.rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Yangxy on 2021/5/29
 * description --创建操作符
 *
 * Create:创建一个被观察者
 * Just:10个重载
 *
 * Note:
 * 1、onComplete 与 onError 是互斥的: 在onComplete 之前调用onError ，不会再执行观察者的onComplete. 在 onComplete 之前调用
 * onError ,不会走观察者的onError,但是会抛出异常。
 */
public class CreateOperatorDemo {
    public static void main(String[] args) {
        System.out.println("__________________________");
        CreateOperatorDemo demo = new CreateOperatorDemo();
        demo.test2();
    }

    Observer observable= new Observer<Object>() {
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

        //观察者模式
        Observable.create(new ObservableOnSubscribe<Object>() {
            //Observable 被观察者
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onNext("3");
                emitter.onComplete();
                emitter.onError(new Throwable("手动丢出异常"));
            }
        }).subscribe(observable);

        //消费者模式
       Disposable disposable =  Observable.create(new ObservableOnSubscribe<Object>() {
            //Observable 被观察者
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
//                emitter.onNext("1");
//                emitter.onNext("2");
//                emitter.onNext("3");
//                emitter.onComplete();
//                emitter.onError(new Throwable("手动丢出异常"));
                //异步操作，耗时操作
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println("accept = " + o);

            }
        },new Consumer<Throwable>(){

            @Override
            public void accept(Throwable throwable) throws Exception {
                //接受到了异常的事件，不会爆出异常崩溃
                System.out.println("Throwable = " + throwable);
            }
        });
    }

    private void test2(){
        Observable.just("1","2","8").subscribe(observable);
        Observable.fromArray("9","10","11","267").subscribe(observable);
    }
}
