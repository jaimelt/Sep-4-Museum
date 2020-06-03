package com.example.android_sep4.view.artwork;

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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

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
    private EditText commentField;
    private EditText minTemp;
    private EditText maxTemp;
    private EditText minLight;
    private EditText maxLight;
    private EditText minCO2;
    private EditText maxCO2;
    private EditText minHum;
    private EditText maxHum;
    private NewArtworkViewModel newArtworkViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_artwork);

        setViewModel();

        Toolbar toolbar = findViewById(R.id.edit_artwork_toolbar);
        setSupportActionBar(toolbar);

        bindViews();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("New artwork");
    }

    private void setViewModel() {
        newArtworkViewModel = new ViewModelProvider(this).get(NewArtworkViewModel.class);
    }

    @Override
    //finish on activity when up navigation is clicked - animation slide to right
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    //TODO: Make scrollview for layout / limit height for multiline description

    public void onSelectImage(View view) {
        Intent intent = new Intent(
                Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    public void onCreateArtwork(View view) {
        int selectedId = typeGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);

        String name = nameField.getText().toString();
        String author = authorField.getText().toString();
        String description = descriptionField.getText().toString();
        String comment = commentField.getText().toString();
        String image = convertImageToString();

        int minTempInt = 0, maxTempInt = 0, minLightInt = 0, maxLightInt = 0, minCO2Int = 0, maxCO2Int = 0, maxHumInt = 0, minHumInt = 0;
        if (!minTemp.getText().toString().isEmpty()) {
            minTempInt = Integer.parseInt(minTemp.getText().toString());
        }
        if (!maxTemp.getText().toString().isEmpty()) {
            maxTempInt = Integer.parseInt(maxTemp.getText().toString());
        }
        if (!minLight.getText().toString().isEmpty()) {
            minLightInt = Integer.parseInt(minLight.getText().toString());
        }
        if (!maxLight.getText().toString().isEmpty()) {
            maxLightInt = Integer.parseInt(maxLight.getText().toString());
        }
        if (!minCO2.getText().toString().isEmpty()) {
            minCO2Int = Integer.parseInt(minCO2.getText().toString());
        }
        if (!maxCO2.getText().toString().isEmpty()) {
            maxCO2Int = Integer.parseInt(maxCO2.getText().toString());
        }
        if (!minHum.getText().toString().isEmpty()) {
            minHumInt = Integer.parseInt(minHum.getText().toString());
        }
        if (!maxHum.getText().toString().isEmpty()) {
            maxHumInt = Integer.parseInt(maxHum.getText().toString());
        }

        int validation = newArtworkViewModel.validateFields(name, author, description, comment, image, minTempInt, maxTempInt, minLightInt, maxLightInt, minCO2Int, maxCO2Int, maxHumInt, minHumInt);
        boolean typeBoolean = newArtworkViewModel.validateGroup(typeGroup);
        if(!typeBoolean)
        {
            Toast.makeText(this, "Select the type of the artwork", Toast.LENGTH_SHORT).show();
        }

        switch (validation) {
            case 1:
                nameField.setError("Enter the name of the artwork");
                break;
            case 2:
                authorField.setError("Enter the author of the artwork");
                break;
            case 3:
                descriptionField.setError("Enter the description of the artwork");
                break;
            case 4:
                commentField.setError("Enter the comment of the artwork");
                break;
            case 5:
                Toast.makeText(this, "Select the image of the artwork", Toast.LENGTH_SHORT).show();;
                break;
            case 6:
                minTemp.setError("Enter the minimum temperature of the artwork");
                break;
            case 7:
                maxTemp.setError("Enter the maximum temperature of the artwork");
                break;
            case 8:
                minCO2.setError("Enter the minimum CO2 value of the artwork");
                break;
            case 9:
                maxCO2.setError("Enter the maximum CO2 value of the artwork");
                break;
            case 10:
                minHum.setError("Enter the minimum humidity of the artwork");
                break;
            case 11:
                maxHum.setError("Enter the maximum humidity of the artwork");
                break;
            case 12:
                minLight.setError("Enter the minimum light value of the artwork");
                break;
            case 13:
                maxLight.setError("Enter the maximum light value of the artwork");
                break;
            case 14:
                nameField.setError("Wrong input");
                break;
            case 15:
                authorField.setError("Wrong input");
                break;
            case 16:
                if(!typeBoolean)
                {
                    break;
                }
                String type = selectedRadioButton.getText().toString();
                newArtworkViewModel.addArtwork(name, author, type, description, comment, image, "Storage", minCO2Int, maxCO2Int, minHumInt, maxHumInt, minLightInt, maxLightInt, minTempInt, maxTempInt);
                finish();
                Toast.makeText(this, name + " artwork added to the list", Toast.LENGTH_SHORT).show();
                break;
        }

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
        imageUploader = findViewById(R.id.imageUploader);
        nameField = findViewById(R.id.nameField);
        authorField = findViewById(R.id.authorField);
        typeGroup = findViewById(R.id.radioType);
        descriptionField = findViewById(R.id.descriptionField);
        commentField = findViewById(R.id.commentsField);
        minTemp = findViewById(R.id.minTemp);
        maxTemp = findViewById(R.id.maxTemp);
        minLight = findViewById(R.id.minLight);
        maxLight = findViewById(R.id.maxLight);
        minCO2 = findViewById(R.id.minCO2);
        maxCO2 = findViewById(R.id.maxCO2);
        minHum = findViewById(R.id.minHum);
        maxHum = findViewById(R.id.maxHum);
    }


}
