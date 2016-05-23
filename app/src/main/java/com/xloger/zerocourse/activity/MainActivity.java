package com.xloger.zerocourse.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.xloger.zerocourse.R;


public class MainActivity extends BaseActivity {

    private DrawerLayout drawerLayout;
    private ActionBar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView(){
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.menu);

        toolbar = getSupportActionBar();
        if (toolbar != null) {
            toolbar.setTitle(R.string.app_name);
            toolbar.setHomeButtonEnabled(true);
            toolbar.setDisplayHomeAsUpEnabled(true);
        }

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                if (toolbar != null) {
                    toolbar.setTitle("菜单");
                }
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (toolbar != null) {
                    toolbar.setTitle(R.string.app_name);
                }
                invalidateOptionsMenu();
            }
        };
        drawerToggle.syncState();
        drawerLayout.addDrawerListener(drawerToggle);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_home:
                        break;
                    case R.id.menu_bbs:

                        break;
                }
                item.setChecked(true);
                drawerLayout.closeDrawers();
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    // The action bar home/up action should open or close the drawer.
    // ActionBarDrawerToggle will take care of this.
        if(drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        //处理其他菜单点击事件
        return super.onOptionsItemSelected(item);
    }
}
