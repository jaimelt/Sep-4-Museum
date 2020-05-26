package com.example.android_sep4.viewmodel;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.android_sep4.viewmodel.artwork.EditArtworkViewModel;

public class ViewModelFactoryInteger implements ViewModelProvider.Factory {
    private Application mApplication;
    private int mParam = 0;


    public ViewModelFactoryInteger(Application application, int mParam) {
        mApplication = application;
        this.mParam = mParam;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new EditArtworkViewModel(mApplication, mParam);
    }
}