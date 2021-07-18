package com.exam.home.dailytest.livadata;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by Yangxy on 2021/7/18
 * description --
 */
public class MyViewModel extends ViewModel {

    public MutableLiveData<String> mText = new MutableLiveData<>();

    public void getTextMsg() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mText.setValue("线程沉睡2000ms发送的文案");
    }
}
