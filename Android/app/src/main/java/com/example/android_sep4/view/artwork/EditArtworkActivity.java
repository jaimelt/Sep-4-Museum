package com.example.android_sep4.view.artwork;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
    private String location;

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
        setToolbar();
        bindViews();
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.edit_artwork_toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTitle("Edit artwork");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }

    private void setViewModel() {
        editArtworkViewModel = new ViewModelProvider(this, new ViewModelFactoryInteger(this.getApplication(), artworkID)).get(EditArtworkViewModel.class);
        editArtworkViewModel.getArtwork().observe(this, artwork -> {
            editArtworkViewModel.getArtwork().removeObservers(this);
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

        location = artwork.getRoomCode();
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

        String name = nameField.getText().toString();
        String author = authorField.getText().toString();
        int validation = editArtworkViewModel.validate(name, author);
        switch (validation) {
            case 1:
                nameField.setError("Enter the name of the artwork");
                break;
            case 2:
                nameField.setError("Invalid input on name");
                break;
            case 3:
                authorField.setError("Enter the name of the author");
                break;
            case 4:
                authorField.setError("Invalid input on author name");
                break;
            case 5:
                String type = selectedRadioButtonType.getText().toString();
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
        }
    }

    public void onEditLocation(View view) {
        int selectedIdLocation = locationGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButtonLocation = findViewById(selectedIdLocation);

        String location = selectedRadioButtonLocation.getText().toString();
        editArtworkViewModel.moveArtwork(artworkID, location);
        moveBtn.setVisibility(View.GONE);

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
        imageHolder = findViewById(R.id.image_holder);
        nameField = findViewById(R.id.name_field);
        authorField = findViewById(R.id.author_field);
        typeGroup = findViewById(R.id.radio_type);
        locationGroup = findViewById(R.id.location_group);
        descriptionField = findViewById(R.id.description_field);
        commentsField = findViewById(R.id.comments_field);
        minTemp = findViewById(R.id.min_temp);
        maxTemp = findViewById(R.id.max_temp);
        minLight = findViewById(R.id.min_light);
        maxLight = findViewById(R.id.max_light);
        minCO2 = findViewById(R.id.min_co2);
        maxCO2 = findViewById(R.id.max_co2);
        minHum = findViewById(R.id.min_hum);
        maxHum = findViewById(R.id.max_hum);
        moveBtn = findViewById(R.id.move_btn);
    }


}
