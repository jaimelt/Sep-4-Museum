package com.example.android_sep4.viewmodel.artwork;

import android.app.Application;
import android.widget.RadioGroup;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.repositories.ArtworksRepository;

public class EditArtworkViewModel extends AndroidViewModel {
    private ArtworksRepository artworksRepository;
    private int id;

    public EditArtworkViewModel(Application application, int id) {
        super(application);
        artworksRepository = ArtworksRepository.getInstance(application);
        this.id = id;
    }

    public void editArtwork(int id, String name, String author, String type, String location, String description, String comment, String image, int position, int maxLight, int minLight, int maxTemperature, int minTemperature, int maxHumidity, int minHumidity, int maxCo2, int minCo2) {
        Artwork artwork = new Artwork(id, name, description, comment, image, type, author, location, position, maxLight, minLight, maxTemperature, minTemperature, maxHumidity, minHumidity, maxCo2, minCo2);
        artworksRepository.editArtwork(artwork);
    }

    public LiveData<Artwork> getArtwork() {
        return artworksRepository.getArtworkById(id);
    }

    public int validateLocation(RadioGroup location) {
        if (validate(location)) {
            return 1;
        } else if(!validate(location)){
            return 0;
        }
        return 2;
    }

    private boolean validate(RadioGroup radioGroup) {
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            // no radio buttons are checked
            return false;
        } else {
            // one of the radio buttons is checked
            return true;
        }

    }

    public void moveArtwork(int artworkID, String location) {
        artworksRepository.moveArtwork(artworkID, location);
    }
}
