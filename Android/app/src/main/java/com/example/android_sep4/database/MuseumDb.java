package com.example.android_sep4.database;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.model.ArtworkMeasurements;
import com.example.android_sep4.model.Room;
import com.example.android_sep4.model.RoomMeasurements;


@Database(entities = {
        Artwork.class,
        Room.class,
        ArtworkMeasurements.class,
        RoomMeasurements.class
        },
        version = 1)
public abstract class MuseumDb extends RoomDatabase {
    //Singleton database - to use the same instance of this
    private static MuseumDb instance;

    //Room subclasses these abstract methods
    public abstract ArtworkDao artworkDao();
    public abstract  RoomDao roomDao();

    //Synchronized - only one thread at the time can access this method
    //fallbackToDestructiveMigration method - deletes the database and creates it from the scratch
    public static synchronized MuseumDb getInstance(Context context) {
        if(instance == null)
        {
            instance = androidx.room.Room.databaseBuilder(context.getApplicationContext(),
                    MuseumDb.class, "museum_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
         return instance;
    }

    //static - so that it can be called in getInstance method
    //Populating database with data
    private static Callback roomCallback = new Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private RoomDao roomDao;

        //Pass MuseumDb to access Dao
        private PopulateDbAsyncTask(MuseumDb database)
        {
            roomDao = database.roomDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            roomDao.insert(new Room("A1", "Special 56m2 room located in the west of Pation room", 9));
            roomDao.insert(new Room("A2", "Special 80m2 room located in the north of A1", 8));
            roomDao.insert(new Room("A3", "Smallest special room of 27m2 located in the north of the museum", 4));
            roomDao.insert(new Room("B1", "35m2 room located in the south of A3", 7));
            roomDao.insert(new Room("B2", "35m2 room located in northeast of the museum", 8));
            roomDao.insert(new Room("B3", "Largest 77m2 room located in the east of the museum ", 12));
            roomDao.insert(new Room("B4", "35m2 room located in the south of the museum", 6));
            roomDao.insert(new Room("Storage", "Storage room to store new artworks", 50));

            roomDao.insert(new RoomMeasurements("A1", 0, 0, 0, 0));
            roomDao.insert(new RoomMeasurements("A2", 0, 0, 0, 0));
            roomDao.insert(new RoomMeasurements("A3", 0, 0, 0, 0));
            roomDao.insert(new RoomMeasurements("B1", 0, 0, 0, 0));
            roomDao.insert(new RoomMeasurements("B2", 0, 0, 0, 0));
            roomDao.insert(new RoomMeasurements("B3", 0, 0, 0, 0));
            roomDao.insert(new RoomMeasurements("B4", 0, 0, 0, 0));
            return null;
        }
    }

}
