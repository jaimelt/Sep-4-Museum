package com.example.android_sep4.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.android_sep4.R;
import com.example.android_sep4.viewmodel.artwork.NewArtworkViewModel;

public class NewArtworkActivity extends AppCompatActivity {

    private static final int IMAGE_PICK_CODE = 1000;
    private ImageView imageHolder;
    private EditText nameField;
    private EditText authorField;
    private EditText typeField;
    private EditText descriptionField;
    private NewArtworkViewModel newArtworkViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_artwork_activity);

        setViewModel();

        imageHolder = findViewById(R.id.imageHolder);
        nameField = findViewById(R.id.nameField);
        authorField = findViewById(R.id.authorField);
        typeField = findViewById(R.id.typeField);
        descriptionField = findViewById(R.id.descriptionField);
    }

    private void setViewModel() {
        newArtworkViewModel = ViewModelProviders.of(this).get(NewArtworkViewModel.class);
        newArtworkViewModel.init();
    }

    //TODO: Make scrollview for layout / limit height for multiline description

    public void onSelectImage(View view)
    {
        Intent intent = new Intent(
                Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    public void onCreateArtwork(View view)
    {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_PICK_CODE) {
            imageHolder.setImageURI(data.getData());
            //TODO: ResultInfo failure when not selecting picture
        }
    }
}
