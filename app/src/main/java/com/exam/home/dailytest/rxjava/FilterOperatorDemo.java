package com.exam.home.dailytest.rxjava;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;

/**
 * Created by Yangxy on 2021/6/3
 * description -- 过滤操作符
 *
 * filter : 筛选满足条件的
 */
public class FilterOperatorDemo {
    public static void main(String[] args) {
        FilterOperatorDemo demo = new FilterOperatorDemo();
        demo.test1();

    }

    private void test1(){
        Observable.range(0, 10).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer > 5;
            }
        }).subscribe(new Observer<Object>(){

            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("d = " + d);
            }

            @Override
            public void onNext(Object o) {
                System.out.println("o = " + o);

            }

            @Override
            public void onError(Throwable e) {
                System.out.println("e = " + e);
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }
}
