package com.che.corvatest;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {
    private final static String TAG = MainViewModel.class.getSimpleName();
    MutableLiveData<ArrayList<String>> data = new MutableLiveData<>();

    public synchronized LiveData<ArrayList<String>> getData(Activity activity) {
        if (data.getValue() == null) {
            data.setValue(new ArrayList<String>());
        }
        if (data.getValue().isEmpty()) {
            loadData(activity);
        }
        return data;
    }

    private void loadData(Activity activity) {
        if (!isPermissionGranted(activity))
            return;

        ArrayList<String> listOfAllImages = new ArrayList<>();
        String absolutePathOfImage;
        Uri uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = { MediaStore.MediaColumns.DATA };

        Cursor cursor = activity.getContentResolver().query(uri, projection, null, null, null);

        int column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);
            listOfAllImages.add(absolutePathOfImage);
        }
        cursor.close();
        Log.d(TAG, "loadData " + listOfAllImages.size());
        data.postValue(listOfAllImages);
    }

    private boolean isPermissionGranted(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                activity.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        return true;
    }

}
