package com.example.android_sep4.requests;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl("https://webapp-200520114720.azurewebsites.net")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static NotificationsEndpoints notificationsEndpoints = retrofit.create(NotificationsEndpoints.class);

    private static ArtworkEndpoints artworkEndpoints = retrofit.create(ArtworkEndpoints.class);

    private static AuthEndpoints authEndpoints = retrofit.create(AuthEndpoints.class);

    private static RoomEndpoints roomEndpoints = retrofit.create(RoomEndpoints.class);

    private static VisitorsEndpoints visitorsEndpoints = retrofit.create(VisitorsEndpoints.class);

    public static ArtworkEndpoints getArtworkEndpoints() {
        return artworkEndpoints;
    }

    public static RoomEndpoints getRoomEndpoints() {
        return roomEndpoints;
    }

    public static AuthEndpoints getAuthEndpoints() {
        return authEndpoints;
    }

    public static VisitorsEndpoints getVisitorsEndpoints() {
        return visitorsEndpoints;
    }

    public static NotificationsEndpoints getNotificationsEndpoints(){return notificationsEndpoints;}
}
