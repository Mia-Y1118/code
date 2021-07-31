package com.exam.home.dailytest.livadata;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.exam.home.dailytest.R;

public class LivaDataActivity extends AppCompatActivity {

    private static final String TAG = LivaDataActivity.class.getSimpleName();
    private TextView mTextView;
    private TextView mSendText;
    private MyViewModel mMyViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liva_data);
        bindView();
        initVM();
    }

    private void bindView() {
        mTextView = findViewById(R.id.tv_show_msg);
        mSendText = findViewById(R.id.tv_send_msg);
        mSendText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMyViewModel.getTextMsg();

            }
        });
    }

    private void initVM() {
//     MyViewModel myViewModel =   new ViewModelProvider().get(MyViewModel.class).
//        mMyViewModel = new MyViewModel();
        mMyViewModel = ViewModelProviders.of(this).get(MyViewModel.class);

        mMyViewModel.mText.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("yxy", "调用: onChanged " + s);
                mTextView.setText("转屏幕接受到");
            }
        });
    }
}
