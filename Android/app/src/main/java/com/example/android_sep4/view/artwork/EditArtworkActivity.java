package com.example.android_sep4.view.artwork;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.android_sep4.R;
import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.viewmodel.ViewModelFactoryInteger;
import com.example.android_sep4.viewmodel.artwork.EditArtworkViewModel;

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
    private EditText minTemp;
    private EditText maxTemp;
    private EditText minLight;
    private EditText maxLight;
    private EditText minCO2;
    private EditText maxCO2;
    private EditText minHum;
    private EditText maxHum;
    private EditText commentsField;
    private Button moveBtn;
    private int artworkID;
    private int artworkPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_artwork);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            artworkID = bundle.getInt("id");
            artworkPosition = bundle.getInt("position");
        }

        System.out.println(artworkID);
        setViewModel();

        Toolbar toolbar = findViewById(R.id.edit_artwork_toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("Edit artwork");

        bindViews();
    }

    @Override
    //finish on activity when up navigation is clicked - animation slide to right
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }

    private void setViewModel() {
        editArtworkViewModel = new ViewModelProvider(this, new ViewModelFactoryInteger(this.getApplication(), artworkID)).get(EditArtworkViewModel.class);
        editArtworkViewModel.getArtwork().observe(this, artwork -> {
            editArtworkViewModel.getArtwork().removeObservers(this);
            Toast.makeText(this, "" + artwork.getName(), Toast.LENGTH_SHORT).show();
            setText(artwork);
        });

    }

    private void setText(Artwork artwork) {
        //TODO: How to set image

        nameField.setText(artwork.getName());
        authorField.setText(artwork.getAuthor());
        descriptionField.setText(artwork.getDescription());
        commentsField.setText(artwork.getComment());
//        imageHolder.setImageURI(editArtworkViewModel.getImage());

        String type = artwork.getType();
        for (int i = 0; i < typeGroup.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) typeGroup.getChildAt(i);
            if (radioButton.getText().toString().equals(type)) {
                radioButton.setChecked(true);
            }
        }

        String location = artwork.getRoomCode();
        for (int i = 0; i < locationGroup.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) locationGroup.getChildAt(i);
            if (radioButton.getText().toString().equals(location)) {
                radioButton.setChecked(true);
            }
        }

        minTemp.setText(String.valueOf(artwork.getMinTemperature()));
        maxTemp.setText(String.valueOf(artwork.getMaxTemperature()));
        minHum.setText(String.valueOf(artwork.getMinHumidity()));
        maxHum.setText(String.valueOf(artwork.getMaxHumidity()));
        minCO2.setText(String.valueOf(artwork.getMinCo2()));
        maxCO2.setText(String.valueOf(artwork.getMaxCo2()));
        minLight.setText(String.valueOf(artwork.getMinLight()));
        maxLight.setText(String.valueOf(artwork.getMaxLight()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_PICK_CODE) {
            imageHolder.setImageURI(data != null ? data.getData() : null);
            //TODO: ResultInfo failure when not selecting picture
        }
    }

    public void onSelectImage(View view) {
        Intent intent = new Intent(
                Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    public void onCreateArtwork(View view) {
        int selectedIdType = typeGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButtonType = findViewById(selectedIdType);

        int selectedIdLocation = locationGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButtonLocation = findViewById(selectedIdLocation);

        String name = "";
        if (!nameField.getText().toString().isEmpty()) {
            name = nameField.getText().toString();
        } else {
            name = nameField.getHint().toString();
        }

        String author = authorField.getText().toString();
        String type = selectedRadioButtonType.getText().toString();
        String location = selectedRadioButtonLocation.getText().toString();
        String description = descriptionField.getText().toString();
        String comment = commentsField.getText().toString();
        String image = convertImageToString();
        int minTempInt = Integer.parseInt(minTemp.getText().toString());
        int maxTempInt = Integer.parseInt(maxTemp.getText().toString());
        int minLightInt = Integer.parseInt(minLight.getText().toString());
        int maxLightInt = Integer.parseInt(maxLight.getText().toString());
        int minCO2Int = Integer.parseInt(minCO2.getText().toString());
        int maxCO2Int = Integer.parseInt(maxCO2.getText().toString());
        int minHumInt = Integer.parseInt(minHum.getText().toString());
        int maxHumInt = Integer.parseInt(maxHum.getText().toString());
        editArtworkViewModel.editArtwork(artworkID, name, author, type, location, description, comment, image, artworkPosition, maxLightInt, minLightInt, maxTempInt, minTempInt, maxHumInt, minHumInt, maxCO2Int, minCO2Int);

        finish();
        Toast.makeText(this, name + " artwork edited", Toast.LENGTH_SHORT).show();
    }

    public void onEditLocation(View view) {
        int selectedIdLocation = locationGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButtonLocation = findViewById(selectedIdLocation);

        int validation = editArtworkViewModel.validateLocation(locationGroup);

        switch (validation) {
            case 1:
                String location = selectedRadioButtonLocation.getText().toString();
                editArtworkViewModel.moveArtwork(artworkID, location);
                moveBtn.setVisibility(View.GONE);
            case 0:
                Toast.makeText(this, "Select the moving location", Toast.LENGTH_SHORT).show();
        }
    }

    private String convertImageToString() {
        imageHolder.buildDrawingCache();
        Bitmap bm = imageHolder.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    private void bindViews() {
        imageHolder = findViewById(R.id.imageHolder);
        nameField = findViewById(R.id.nameField);
        authorField = findViewById(R.id.authorField);
        typeGroup = findViewById(R.id.radioType);
        locationGroup = findViewById(R.id.locationGroup1);
        descriptionField = findViewById(R.id.descriptionField);
        commentsField = findViewById(R.id.commentsField);
        minTemp = findViewById(R.id.minTemp);
        maxTemp = findViewById(R.id.maxTemp);
        minLight = findViewById(R.id.minLight);
        maxLight = findViewById(R.id.maxLight);
        minCO2 = findViewById(R.id.minCO2);
        maxCO2 = findViewById(R.id.maxCO2);
        minHum = findViewById(R.id.minHum);
        maxHum = findViewById(R.id.maxHum);
        moveBtn = findViewById(R.id.moveBtn);
    }


}
