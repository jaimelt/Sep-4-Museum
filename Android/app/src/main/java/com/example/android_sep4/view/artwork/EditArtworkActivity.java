package com.example.android_sep4.view.artwork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.android_sep4.R;

public class EditArtworkActivity extends AppCompatActivity {

    private TextView numberField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

//        numberField = findViewById(R.id.numberField);
//        Bundle bundle = getIntent().getExtras();
//
//        if(bundle != null && bundle.containsKey(ArtworksTab.EXTRA_ARTWORK)) {
//            int number = bundle.getInt(ArtworksTab.EXTRA_ARTWORK);
//            numberField.setText(number);
//        }
    }
}
