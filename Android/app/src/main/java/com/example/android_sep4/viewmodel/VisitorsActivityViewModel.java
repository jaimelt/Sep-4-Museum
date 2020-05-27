package com.example.android_sep4.viewmodel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;

import com.example.android_sep4.repositories.VisitorsRepository;

public class VisitorsActivityViewModel extends AndroidViewModel {
    private VisitorsRepository visitorsRepository;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public VisitorsActivityViewModel(@NonNull Application application) {
        super(application);
        visitorsRepository = VisitorsRepository.getInstance();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void sendVisitorsData() {
        visitorsRepository.sendVisitorsData();
    }
}
