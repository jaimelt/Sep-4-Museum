package com.example.android_sep4.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.android_sep4.viewmodel.roomList.RoomArtworksViewModel;
import com.example.android_sep4.viewmodel.rooms.RoomA1ViewModel;
import com.example.android_sep4.viewmodel.rooms.RoomA2ViewModel;
import com.example.android_sep4.viewmodel.rooms.RoomA3ViewModel;
import com.example.android_sep4.viewmodel.rooms.RoomB1ViewModel;
import com.example.android_sep4.viewmodel.rooms.RoomB2ViewModel;
import com.example.android_sep4.viewmodel.rooms.RoomB3ViewModel;
import com.example.android_sep4.viewmodel.rooms.RoomB4ViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Application application;
    private String param;


    public ViewModelFactory(Application application, String param) {
        this.application = application;
        this.param = param;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass == RoomArtworksViewModel.class) {
            return (T) new RoomArtworksViewModel(application, param);
        } else if (modelClass == RoomA1ViewModel.class) {
            return (T) new RoomA1ViewModel(application, param);
        } else if (modelClass == RoomA2ViewModel.class) {
            return (T) new RoomA2ViewModel(application, param);
        } else if (modelClass == RoomA2ViewModel.class) {
            return (T) new RoomA3ViewModel(application, param);
        } else if (modelClass == RoomA3ViewModel.class) {
            return (T) new RoomB1ViewModel(application, param);
        } else if (modelClass == RoomB1ViewModel.class) {
            return (T) new RoomA2ViewModel(application, param);
        } else if (modelClass == RoomB2ViewModel.class) {
            return (T) new RoomB2ViewModel(application, param);
        } else if (modelClass == RoomB3ViewModel.class) {
            return (T) new RoomB3ViewModel(application, param);
        }else if (modelClass == RoomB4ViewModel.class) {
            return (T) new RoomB4ViewModel(application, param);
        } else {
            return super.create(modelClass);
        }
    }
}