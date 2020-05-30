package com.example.android_sep4.viewmodel.artwork;

import android.app.Application;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.model.ArtworkMeasurements;
import com.example.android_sep4.repositories.ArtworksRepository;

public class NewArtworkViewModel extends AndroidViewModel {
    private ArtworksRepository artworksRepository;
    private Artwork artwork;

    public NewArtworkViewModel(Application application) {
        super(application);
        artworksRepository = ArtworksRepository.getInstance(application);
    }

    public void addArtwork(String name, String author, String type, String description, String comment, String image, String location, int minTempInt, int maxTempInt, int minLightInt, int maxLightInt, int minCO2Int, int maxCO2Int, int maxHumInt, int minHumInt) {
        int id = 0;
        artwork = new Artwork(id, name, description, comment, image, type, author, location, 1, maxLightInt, minLightInt, maxTempInt, minTempInt, maxHumInt, minHumInt, maxCO2Int, minCO2Int);
        artworksRepository.addNewArtwork(artwork);

    }

    public int validateFields(String name, String author, RadioGroup type, String description, String comment, String image, int minTempInt, int maxTempInt, int minLightInt, int maxLightInt, int minCO2Int, int maxCO2Int, int maxHumInt, int minHumInt) {
        if (name.isEmpty()) {
            return 1;
        } else if (author.isEmpty()) {
            return 2;
        } else if (!validate(type)) {
            return 3;
        } else if (description.isEmpty()) {
            return 4;
        } else if (comment.isEmpty()) {
            return 5;
        } else if (image.isEmpty()) {
            return 6;
        } else if (minTempInt <= 0) {
            return 7;
        } else if (maxTempInt <= 0) {
            return 8;
        } else if (minCO2Int <= 0) {
            return 9;
        } else if (maxCO2Int <= 0) {
            return 10;
        } else if (minHumInt <= 0) {
            return 11;
        } else if (maxHumInt <= 0) {
            return 12;
        } else if (minLightInt <= 0) {
            return 13;
        } else if (maxLightInt <= 0) {
            return 14;
        } else {
            return 15;
        }
    }

    private boolean validate(RadioGroup radioGroup) {
        if (radioGroup.getCheckedRadioButtonId() == -1)
        {
            // no radio buttons are checked
            return false;
        }
        else
        {
            // one of the radio buttons is checked
            return true;
        }

    }
}
