package com.example.android_sep4.viewmodel.artwork;

import android.app.Application;
import android.widget.RadioGroup;

import androidx.lifecycle.AndroidViewModel;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.model.Validator;
import com.example.android_sep4.repositories.ArtworksRepository;

public class NewArtworkViewModel extends AndroidViewModel {
    private ArtworksRepository artworksRepository;
    private Artwork artwork;
    private Validator validator;

    public NewArtworkViewModel(Application application) {
        super(application);
        artworksRepository = ArtworksRepository.getInstance(application);
        this.validator = new Validator();
    }

    public void addArtwork(String name, String author, String type, String description, String comment, String image, String location, int minTempInt, int maxTempInt, int minLightInt, int maxLightInt, int minCO2Int, int maxCO2Int, int maxHumInt, int minHumInt) {
        int id = 0;
        artwork = new Artwork(id, name, description, comment, image, type, author, location, 1, maxLightInt, minLightInt, maxTempInt, minTempInt, maxHumInt, minHumInt, maxCO2Int, minCO2Int);
        artworksRepository.addNewArtwork(artwork);

    }

    public int validateFields(String name, String author, String description, String comment, String image, int minTempInt, int maxTempInt, int minLightInt, int maxLightInt, int minCO2Int, int maxCO2Int, int maxHumInt, int minHumInt) {
        return validator.validateAddArtworkFields(name, author, description, comment, image, minTempInt, maxTempInt, minLightInt, maxLightInt, minCO2Int, maxCO2Int, maxHumInt, minHumInt);
    }

    public boolean validateGroup(RadioGroup radioGroup) {
        return radioGroup.getCheckedRadioButtonId() != -1;
    }
}
