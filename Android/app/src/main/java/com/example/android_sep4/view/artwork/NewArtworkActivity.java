package com.example.android_sep4.view.artwork;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.android_sep4.R;
import com.example.android_sep4.viewmodel.artwork.NewArtworkViewModel;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

public class NewArtworkActivity extends AppCompatActivity {

    private static final int IMAGE_PICK_CODE = 1000;
    private ImageView imageHolder;
    private ImageView imageUploader;
    private EditText nameField;
    private EditText authorField;
    private RadioGroup typeGroup;
    private EditText descriptionField;
    private NewArtworkViewModel newArtworkViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_artwork);

        setViewModel();

        Toolbar toolbar = findViewById(R.id.edit_artwork_toolbar);
        setSupportActionBar(toolbar);

        imageHolder = findViewById(R.id.imageHolder);
        imageUploader = findViewById(R.id.imageUploader);
        nameField = findViewById(R.id.nameField);
        authorField = findViewById(R.id.authorField);
        typeGroup = findViewById(R.id.radioType);
        descriptionField = findViewById(R.id.descriptionField);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("New artwork");
    }

    private void setViewModel() {
        newArtworkViewModel = new ViewModelProvider(this).get(NewArtworkViewModel.class);
        newArtworkViewModel.init();
    }

    @Override
    //finish on activity when up navigation is clicked - animation slide to right
    public boolean onSupportNavigateUp() {
        finish();
        return true;
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
        int selectedId = typeGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);

        String name = nameField.getText().toString();
        String author = authorField.getText().toString();
        String type = selectedRadioButton.getText().toString();
        String description = descriptionField.getText().toString();
        String image = convertImageToString();
        newArtworkViewModel.addArtwork(name, author, type, description, image, "Storage");
        finish();
        Toast.makeText(this, name + " artwork added to the list", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_PICK_CODE) {
            imageHolder.setImageURI(data != null ? data.getData() : null);
            imageUploader.setVisibility(View.INVISIBLE);
            //TODO: ResultInfo failure when not selecting picture
        }
    }

    private String convertImageToString()
    {
        imageHolder.buildDrawingCache();
        Bitmap bm = imageHolder.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }


}
