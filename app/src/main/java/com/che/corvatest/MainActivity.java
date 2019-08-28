package com.che.corvatest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import android.view.Menu;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GalleryFragment.OnFragmentInteractionListener, TabLayout.BaseOnTabSelectedListener {

    private final static String TAG = MainActivity.class.getSimpleName();
    MainViewModel model;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        model = ViewModelProviders.of(this).get(MainViewModel.class);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TabLayout tabLayout = findViewById(R.id.tab_view);
        tabLayout.addOnTabSelectedListener(this);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[Arrays.binarySearch(permissions, Manifest.permission.READ_EXTERNAL_STORAGE)] == PackageManager.PERMISSION_GRANTED)
            model.getData(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        navigate(item.getItemId());

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void navigate(int target) {
        switch (navController.getCurrentDestination().getId()) {
            case R.id.galleryFragment:
                switch (target) {
                    case R.id.nav_color:
                    case 1:
                        navController.navigate(R.id.action_galleryFragment_to_colorFragment);
                        break;
                    case R.id.nav_color2:
                    case 2:
                        navController.navigate(R.id.action_galleryFragment_to_colorFragment2);
                        break;
                    case R.id.nav_color3:
                    case 3:
                        navController.navigate(R.id.action_galleryFragment_to_colorFragment3);
                        break;
                }
                break;
            case R.id.colorFragment:
                switch (target) {
                    case R.id.nav_gallery:
                    case 0:
                        navController.navigate(R.id.action_colorFragment_to_galleryFragment);
                        break;
                    case R.id.nav_color2:
                    case 2:
                        navController.navigate(R.id.action_colorFragment_to_colorFragment2);
                        break;
                    case R.id.nav_color3:
                    case 3:
                        navController.navigate(R.id.action_colorFragment_to_colorFragment3);
                        break;
                }
                break;
            case R.id.colorFragment2:
                switch (target) {
                    case R.id.nav_gallery:
                    case 0:
                        navController.navigate(R.id.action_colorFragment2_to_galleryFragment);
                        break;
                    case R.id.nav_color:
                    case 1:
                        navController.navigate(R.id.action_colorFragment2_to_colorFragment);
                        break;
                    case R.id.nav_color3:
                    case 3:
                        navController.navigate(R.id.action_colorFragment2_to_colorFragment3);
                        break;
                }
                break;
            case R.id.colorFragment3:
                switch (target) {
                    case R.id.nav_gallery:
                    case 0:
                        navController.navigate(R.id.action_colorFragment3_to_galleryFragment);
                        break;
                    case R.id.nav_color:
                    case 1:
                        navController.navigate(R.id.action_colorFragment3_to_colorFragment);
                        break;
                    case R.id.nav_color2:
                    case 2:
                        navController.navigate(R.id.action_colorFragment3_to_colorFragment2);
                        break;
                }
                break;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.d(TAG, "onFragmentInteraction");
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        navigate(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    private static class Corva {
        /*
    Тестовое задание по Android

    требуется написать приложение с боковым меню и таб баром на 4 вкладки.
    Попасть на вкладки можно как из меню, так и с таб бара.
    На основной вкладке нужно расположить сетку из фотографий с галереи, остальные - пустые, с разным background color
    Необходимо реализовать это через фрагменты

    Фрагмент = Fragment, это имя компонента.
    Есть activity based навигация а есть fragment based
    Версия Android любая
    Из какой галереи - из системной
    */
    }
}
