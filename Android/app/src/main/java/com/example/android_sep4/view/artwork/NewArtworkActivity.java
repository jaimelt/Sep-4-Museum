package com.example.android_sep4.view.artwork;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android_sep4.R;
import com.example.android_sep4.viewmodel.artwork.NewArtworkViewModel;

import java.io.ByteArrayOutputStream;

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
        setContentView(R.layout.activity_edit_artwork);

        setViewModel();

        imageHolder = findViewById(R.id.imageHolder);
        nameField = findViewById(R.id.nameField);
        authorField = findViewById(R.id.authorField);
        typeField = findViewById(R.id.typeField);
        descriptionField = findViewById(R.id.descriptionField);
    }

    private void setViewModel() {
        newArtworkViewModel = new ViewModelProvider(this).get(NewArtworkViewModel.class);
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
        String name = nameField.getText().toString();
        String author = authorField.getText().toString();
        String type = typeField.getText().toString();
        String description = descriptionField.getText().toString();
        String image = convertImageToString();
        newArtworkViewModel.addArtwork(name, author, type, description, image);
        finish();
        Toast.makeText(this, "Artwork added to the list", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_PICK_CODE) {
            imageHolder.setImageURI(data != null ? data.getData() : null);
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
