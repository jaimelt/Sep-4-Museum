package com.example.android_sep4.model;

import com.example.android_sep4.requests.ArtworkResponse;

import java.util.ArrayList;

public class Artworks {
    private ArrayList<ArtworkResponse> artworks;

    public Artworks(ArrayList<ArtworkResponse> artworks) {
        this.artworks = artworks;
    }

    public ArrayList<ArtworkResponse> getArtworks() {
        return artworks;
    }
}
