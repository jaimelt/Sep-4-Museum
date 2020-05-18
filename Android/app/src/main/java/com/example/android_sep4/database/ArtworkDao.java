package com.example.android_sep4.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;

import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.model.ArtworkMeasurements;

import java.util.List;


@Dao
public abstract class ArtworkDao {

    @Transaction
    void insert(ArtworkWithMeasurements artwork){
        insert(artwork.artwork);
        insert(artwork.roomMeasurements);
    }

    @Transaction
    void delete(ArtworkWithMeasurements artwork){
        delete(artwork.artwork);
        delete(artwork.roomMeasurements);
    }

    @Transaction
    void update(ArtworkWithMeasurements artwork){
        update(artwork.artwork);
        update(artwork.roomMeasurements);
    }

    @Query("SELECT * FROM Artwork")
    public abstract List<ArtworkWithMeasurements> getAllArtworks();

    @Query("SELECT * FROM Artwork")
    public abstract LiveData<List<ArtworkWithMeasurements>> getAllLiveArtworks();

    @Insert
    abstract void insert(Artwork artwork);

    @Insert
    abstract void insert(ArtworkMeasurements artworkMeasurements);

    @Delete
    abstract void delete(Artwork artwork);

    @Delete
    abstract void delete(ArtworkMeasurements artworkMeasurements);

    @Update
    abstract void update(Artwork artwork);

    @Update
    abstract void update(ArtworkMeasurements artworkMeasurements);
}
