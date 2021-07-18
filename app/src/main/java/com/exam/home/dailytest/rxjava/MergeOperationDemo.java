package com.exam.home.dailytest.rxjava;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Yangxy on 2021/6/3
 * description -- 组合操作符: 把事件进行合并
 */
public class MergeOperationDemo {
    public static void main(String[] args) {
        MergeOperationDemo demo = new MergeOperationDemo();
        demo.test1();
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

    public void test1() {
        //串行
        Observable.concat(Observable.just("2222"), Observable.just("222")).subscribe(observable);
        //并行
        Observable.merge(Observable.just("hahahh"), Observable.just("hahha"), Observable.just("duduud")).subscribe(observable);
    }
}
