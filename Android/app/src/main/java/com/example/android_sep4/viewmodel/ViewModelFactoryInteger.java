package com.example.android_sep4.viewmodel;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.android_sep4.viewmodel.artwork.EditArtworkViewModel;

public class ViewModelFactoryInteger implements ViewModelProvider.Factory {
    private Application mApplication;
    private int mParam;


    public ViewModelFactoryInteger(Application application, int param) {
        mApplication = application;
        mParam = param;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new EditArtworkViewModel(mApplication, mParam);
    }
}