package com.example.android_sep4.database;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.model.ArtworkMeasurements;

public class ArtworkWithMeasurements {
    @Embedded
    public Artwork artwork;

    @Relation(parentColumn = "id", entityColumn = "id", entity = ArtworkMeasurements.class)
    public ArtworkMeasurements roomMeasurements;
}
