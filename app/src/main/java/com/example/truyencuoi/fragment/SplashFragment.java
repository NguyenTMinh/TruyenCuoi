package com.example.truyencuoi.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.truyencuoi.OnActionClick;
import com.example.truyencuoi.R;

public class SplashFragment extends Fragment {
    private OnActionClick actionClick;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                actionClick.callBack();
            }
        },3000);
        return inflater.inflate(R.layout.fragment_splash,container,false);
    }

    public void setActionClick(OnActionClick actionClick) {
        this.actionClick = actionClick;
    }
}
