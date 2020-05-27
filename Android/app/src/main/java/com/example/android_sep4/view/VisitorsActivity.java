package com.example.android_sep4.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android_sep4.R;
import com.example.android_sep4.viewmodel.VisitorsActivityViewModel;
import com.example.android_sep4.viewmodel.roomList.RoomsTabViewModel;

public class VisitorsActivity extends AppCompatActivity {
    private Button visitorsBtn;
    private VisitorsActivityViewModel visitorsActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitors);

        setViewModel();

        visitorsBtn = findViewById(R.id.visitorsBtn);

        visitorsBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                visitorsActivityViewModel.sendVisitorsData();
            }
        });
    }

    public void setViewModel() {
        visitorsActivityViewModel = new ViewModelProvider(this).get(VisitorsActivityViewModel.class);

    }


}
