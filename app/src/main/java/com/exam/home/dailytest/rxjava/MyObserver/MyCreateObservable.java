package com.exam.home.dailytest.rxjava.MyObserver;

/**
 * Created by Yangxy on 2021/6/13
 * description --
 */
public class MyCreateObservable<T> extends MyObservable {

    private ObservableOnSubcribe observableOnSubcribe;

    MyCreateObservable(ObservableOnSubcribe observableOnSubcribe){
        this.observableOnSubcribe = observableOnSubcribe;
    }

    @Override
    protected void subscribeActual(MyObserver myObservable) {
        myObservable.onSubscribe();
        CreateEmittor emittor = new CreateEmittor(myObservable);
        observableOnSubcribe.subscribe(emittor);
    }

    static class CreateEmittor<T>  implements MyEmitor<T>{

        private MyObserver myObserver;

        CreateEmittor(MyObserver myObserver){
            this.myObserver = myObserver;
        }

        @Override
        public void onNext(T e) {
            myObserver.onNext(e);
        }

        @Override
        public void onError(Throwable e) {
            myObserver.onError(e);

        }

        @Override
        public void onComplete(T e) {
            myObserver.onComplete(e);

        }
    }
}
