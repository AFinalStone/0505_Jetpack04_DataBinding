package com.example.demo.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;

import com.example.demo.listener.ListenerActivity;
import com.example.demo.observablefied.ObservableFieldActivity;
import com.example.demo.recycler.RecyclerViewActivity;
import com.example.demo.viewpager.ViewPagerActivity;
import com.example.widget.BR;
import com.example.widget.R;
import com.example.widget.databinding.ActivityMainBindTest;

public class MainActivity extends AppCompatActivity {

    ActivityInfo activityInfo;
    ActivityInfoHandler activityInfoHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置页面
        activityInfo = new ActivityInfo();
        activityInfo.setName("RecyclerView列表条目");
        activityInfo.setActivityName(RecyclerViewActivity.class.getSimpleName());
        activityInfo.setFullActivityName(RecyclerViewActivity.class.getName());
        activityInfoHandler = new ActivityInfoHandler();
        ActivityMainBindTest activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setActivityInfo(activityInfo);
        activityMainBinding.setActivityInfoHandler(activityInfoHandler);
        activityInfo.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                Log.d("MainActivity", "propertyId = " + propertyId);
                if (propertyId == BR.activityName) {
                    Log.d("MainActivity", "activityInfo.activityName.propertyId = " + propertyId);
                }
            }
        });


    }

    public class ActivityInfoHandler {

        public void changeOnlyProperty() {
            Log.d("ActivityInfoHandler", "changeOnlyProperty方法被执行");
            activityInfo.setFullActivityName("更新单一属性");
            activityInfo.setActivityName("更新单一属性");
        }

        public void changeAllProperty() {
            Log.d("ActivityInfoHandler", "changeAllProperty方法被执行");
            activityInfo.setFullActivityName("更新全部属性");
            activityInfo.setName("更新全部属性");
        }

        public void toObservableFieldActivity() {
            startActivity(new Intent(MainActivity.this, ObservableFieldActivity.class));
        }

        public void toListenerActivity() {
            startActivity(new Intent(MainActivity.this, ListenerActivity.class));
        }

        public void toRecyclerViewActivity() {
            startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
        }

        public void toViewPagerActivity() {
            startActivity(new Intent(MainActivity.this, ViewPagerActivity.class));
        }

    }

}
