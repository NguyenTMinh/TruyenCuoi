package com.example.truyencuoi.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.truyencuoi.OnActionClick;
import com.example.truyencuoi.R;
import com.example.truyencuoi.fragment.FavoriteFragment;
import com.example.truyencuoi.fragment.ListSingleStoryFragment;
import com.example.truyencuoi.fragment.ListTopicStoryFragment;
import com.example.truyencuoi.fragment.SplashFragment;
import com.example.truyencuoi.fragment.StoryViewPagerFragment;
import com.example.truyencuoi.model.Story;
import com.example.truyencuoi.model.TopicStory;
import com.example.truyencuoi.util.FileUtil;
import com.example.truyencuoi.model.Stories;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnActionClick, NavigationView.OnNavigationItemSelectedListener {
    private SplashFragment splashFragment;
    private ListTopicStoryFragment listTopicStoryFragment;
    private FavoriteFragment favoriteFragment;
    private ListSingleStoryFragment listSingleStoryFragment;
    private StoryViewPagerFragment storyViewPagerFragment;
    private DrawerLayout drawerLayout;
    private Stories stories;
    private int posTopicOnSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stories = new Stories(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        getSupportActionBar().setTitle("");
        getSupportActionBar().hide();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.nav_open,R.string.nav_clsoe);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        splashFragment = new SplashFragment();
        splashFragment.setActionClick(this);
        storyViewPagerFragment = new StoryViewPagerFragment();
        storyViewPagerFragment.setActionClick(this);
        listSingleStoryFragment = new ListSingleStoryFragment();
        listSingleStoryFragment.setActionClick(this);
        listTopicStoryFragment = new ListTopicStoryFragment();
        listTopicStoryFragment.setActionClick(this);
        favoriteFragment = new FavoriteFragment();
        favoriteFragment.setActionClick(this);

        for (int i = 0; i < stories.getTopicStoryList().size(); i++) {
            for (int j = 0; j < stories.getTopicStoryList().get(i).getListStory().size(); j++) {
                if(stories.getTopicStoryList().get(i).getListStory().get(j).isFavorite()){
                    favoriteFragment.addFavStory(stories.getTopicStoryList().get(i).getListStory().get(j));
                }
            }
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view,splashFragment).commit();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_categories);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else if(listSingleStoryFragment.isAdded()){
            super.onBackPressed();
            storyViewPagerFragment.setReturnFrom(false);
        }else if(getSupportFragmentManager().getBackStackEntryCount() > 0){
            super.onBackPressed();
        } else{
            FileUtil.saveFavorite(this,stories.getTopicStoryList());
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_categories:{
                for (int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); i++) {
                    getSupportFragmentManager().popBackStack();
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_view,listTopicStoryFragment).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            }
            case R.id.nav_favorite:{
                for (int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); i++) {
                    getSupportFragmentManager().popBackStack();
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_view,favoriteFragment).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            }
        }
        return true;
    }

    //override for actionclick interface
    @Override
    public void callBack() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view,listTopicStoryFragment).commit();
        getSupportActionBar().show();
    }

    @Override
    public List<TopicStory> getStory() {
        return stories.getTopicStoryList();
    }

    @Override
    public ListSingleStoryFragment getListSingleFrag() {
        return listSingleStoryFragment;
    }

    @Override
    public void changeListSingleFrag(int pos) {
        posTopicOnSelected = pos;
        listSingleStoryFragment.setStories(stories.getTopicStoryList().get(pos).getListStory());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right)
                .replace(R.id.fragment_container_view,listSingleStoryFragment)
                .addToBackStack("add");
        transaction.commit();
    }

    @Override
    public void changeListViewpager(List<Story> list) {
        storyViewPagerFragment.setStories(list);
        if(listSingleStoryFragment.isAdded()) {
            storyViewPagerFragment.setTranData(listSingleStoryFragment.getStoryAdapter());
        }else if(favoriteFragment.isAdded()){
            storyViewPagerFragment.setTranData(favoriteFragment.getAdapter());
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().
                setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right);
        transaction.replace(R.id.fragment_container_view,storyViewPagerFragment);
        transaction.addToBackStack("add").commit();
    }

    @Override
    public boolean isReturnFromSVPF() {
        return storyViewPagerFragment.isReturnFrom();
    }

    @Override
    public void addToFav(int pos) {
        favoriteFragment.addFavStory(stories.getTopicStoryList().get(posTopicOnSelected).getListStory().get(pos));
    }

    @Override
    public void removeFromFav(Story story) {
        favoriteFragment.removeFavStory(story);
    }

}