package com.example.truyencuoi.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.truyencuoi.Constant;
import com.example.truyencuoi.OnActionClick;
import com.example.truyencuoi.R;
import com.example.truyencuoi.fragment.HomeFragment;
import com.example.truyencuoi.fragment.SplashFragment;
import com.example.truyencuoi.fragment.ViewStoryFragment;
import com.example.truyencuoi.model.Story;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnActionClick{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SplashFragment splashFragment = new SplashFragment();
        splashFragment.setActionClick(this);
        tranFragment(R.id.main_container,splashFragment,false,0,0,0,0);
    }

    public void tranFragment(int layouID,
                             Fragment fragment,
                             boolean addToBackStack,
                             int animEnter, int animExit, int animPopEnter, int animPopExit){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(animEnter,animExit,animPopEnter,animPopExit);
        transaction.replace(layouID,fragment);
        if(addToBackStack) {
            transaction.addToBackStack("add");
        }
        transaction.commit();
    }

    @Override
    public void callBack(int key, Object data, Object subData) {
        switch (key){
            case Constant.KEY_HOME:{
                HomeFragment homeFragment = new HomeFragment();
                homeFragment.setActionClick(this);
                tranFragment(R.id.main_container,homeFragment,false,0,0,0,0);
                break;
            }
            case Constant.KEY_STORY:{
                ViewStoryFragment viewStoryFragment = new ViewStoryFragment((List<Story>) data, (Integer) subData);
                tranFragment(R.id.main_container,viewStoryFragment,true,
                        R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right);
                break;
            }
        }
    }
}