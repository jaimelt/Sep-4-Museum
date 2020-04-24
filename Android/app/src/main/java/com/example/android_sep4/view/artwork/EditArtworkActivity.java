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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.android_sep4.R;
import com.example.android_sep4.viewmodel.artwork.EditArtworkViewModel;
import com.whygraphics.multilineradiogroup.MultiLineRadioGroup;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

public class EditArtworkActivity extends AppCompatActivity {
    private static final int IMAGE_PICK_CODE = 1001;
    private EditArtworkViewModel editArtworkViewModel;
    private ImageView imageHolder;
    private EditText nameField;
    private EditText authorField;
    private RadioGroup typeGroup;
    private RadioGroup locationGroup;
    private EditText descriptionField;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_artwork);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.containsKey(ArtworksTab.EXTRA_ARTWORK)) {
            position = bundle.getInt(ArtworksTab.EXTRA_ARTWORK);
        }
        setViewModel();

        Toolbar toolbar = findViewById(R.id.edit_artwork_toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("Edit artwork");

        imageHolder = findViewById(R.id.imageHolder);
        nameField = findViewById(R.id.nameField);
        authorField = findViewById(R.id.authorField);
        typeGroup = findViewById(R.id.radioType);
        locationGroup = findViewById(R.id.locationGroup1);
        descriptionField = findViewById(R.id.descriptionField);

        setText();
    }

    @Override
    //finish on activity when up navigation is clicked - animation slide to right
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }

    private void setViewModel() {
        editArtworkViewModel = new ViewModelProvider(this).get(EditArtworkViewModel.class);
        editArtworkViewModel.init(position);
    }

    private void setText()
    {
        //TODO: How to set image
        nameField.setText(editArtworkViewModel.getName());
        authorField.setHint(editArtworkViewModel.getAuthor());
        descriptionField.setHint(editArtworkViewModel.getDescription());
        imageHolder.setImageURI(editArtworkViewModel.getImage());

        String type = editArtworkViewModel.getType();
        for(int i = 0; i < typeGroup.getChildCount(); i ++)
        {
            RadioButton radioButton =  (RadioButton)typeGroup.getChildAt(i);
            if(radioButton.getText().toString().equals(type))
            {
                radioButton.setChecked(true);
            }
        }

        String location = editArtworkViewModel.getLocation();
        for(int i = 0; i < locationGroup.getChildCount(); i++)
        {
            RadioButton radioButton = (RadioButton) locationGroup.getChildAt(i);
            if(radioButton.getText().toString().equals(location))
            {
                radioButton.setChecked(true);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_PICK_CODE) {
            imageHolder.setImageURI(data != null ? data.getData() : null);
            //TODO: ResultInfo failure when not selecting picture
        }
    }

    public void onSelectImage(View view)
    {
        Intent intent = new Intent(
                Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    public void onCreateArtwork(View view)
    {
        int selectedIdType = typeGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButtonType = findViewById(selectedIdType);

        int selectedIdLocation = locationGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButtonLocation = findViewById(selectedIdLocation);
        String name = "";
        if(!nameField.getText().toString().isEmpty())
        {
            name = nameField.getText().toString();
        }
        else
        {
            name = nameField.getHint().toString();
        }

        String author = authorField.getText().toString();
        String type = selectedRadioButtonType.getText().toString();
        String location = selectedRadioButtonLocation.getText().toString();
        String description = descriptionField.getText().toString();
        String image = convertImageToString();
        editArtworkViewModel.editArtwork(name, author, type, location, description, image, position);
        finish();
        Toast.makeText(this, name + " artwork edited", Toast.LENGTH_SHORT).show();
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
