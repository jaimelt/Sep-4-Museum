package com.example.android_sep4.view.artwork;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.example.android_sep4.adapters.ArtworksAdapter;
import com.example.android_sep4.model.Artwork;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class ArtworksTabTest {
    @Mock
    Context context;
    @InjectMocks
    private ArtworksTab artworksTab;
    private RecyclerView recyclerView;
    private ArrayList<Artwork> artworks;
    private Artwork artwork;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recyclerView = mock(RecyclerView.class);
        MockitoAnnotations.initMocks(this);
        artworksTab = mock(ArtworksTab.class);
        artworks = new ArrayList<>();
        artwork = Mockito.mock(Artwork.class);
    }

    @Test
    public void checkIfRecyclerViewClicked() {
        artworks.add(artwork);
        ArtworksAdapter adapter = Mockito.mock(ArtworksAdapter.class);
        adapter.setArtworks(artworks);
        recyclerView.setAdapter(adapter);

        //Check if method was invoked with our object
        int itemCount = Mockito.verify(adapter).getItemCount();
        Assert.assertEquals(1, itemCount, 0);
    }
}