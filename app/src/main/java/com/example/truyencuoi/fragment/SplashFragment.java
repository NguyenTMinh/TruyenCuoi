package com.example.truyencuoi.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.truyencuoi.Constant;
import com.example.truyencuoi.OnActionClick;
import com.example.truyencuoi.R;

public class SplashFragment extends Fragment {
    private OnActionClick actionClick;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash,container,false);
    }

    @Override
    public void onResume() {
        new Handler().postDelayed(() -> actionClick.callBack(Constant.KEY_HOME,null,null),3000);
        super.onResume();
    }

    public void setActionClick(OnActionClick actionClick) {
        this.actionClick = actionClick;
    }
}
