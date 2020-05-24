package com.example.android_sep4.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.android_sep4.model.User;
import com.example.android_sep4.requests.AuthEndpoints;
import com.example.android_sep4.requests.ServiceGenerator;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private static AuthRepository instance;
    private MutableLiveData<Boolean> isValidating = new MutableLiveData<>();
    private Boolean valid = false;

    public static AuthRepository getInstance() {
        if (instance == null) {
            instance = new AuthRepository();
        }
        return instance;
    }

    private static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (byte b : data) {
            int halfbyte = (b >>> 4) & 0x0F;
            int two_halves = 0;
            do {
                buf.append(halfbyte <= 9 ? (char) ('0' + halfbyte) : (char) ('a' + halfbyte - 10));
                halfbyte = b & 0x0F;
            } while (two_halves++ < 1);
        }
        return buf.toString();
    }

    private static String hashSHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] textBytes = text.getBytes(StandardCharsets.ISO_8859_1);
        md.update(textBytes, 0, textBytes.length);
        byte[] sha1hash = md.digest();
        return convertToHex(sha1hash);
    }

    public boolean validateLogin(String email, String password) {
        isValidating.setValue(true);
        try {
            String hashedPassword = hashSHA1(password);
            System.out.println(hashedPassword);

//            User user = new User(email, hashedPassword);
//
//            AuthEndpoints endpoints = RetrofitClientInstance.getRetrofitInstance().create(AuthEndpoints.class);
//
//            Call<Boolean> call = endpoints.validateLogin(user);
//            call.enqueue(new Callback<Boolean>() {
//                @Override
//                public void onResponse(Call<Boolean> call, Response<Boolean> response) {
//                    valid = response.body();
//                    isValidating.setValue(false);
//                }
//
//                @Override
//                public void onFailure(Call<Boolean> call, Throwable t) {
//
//                }
//            });

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        isValidating.setValue(false);
                    }
                }, 2000);

        return valid;
    }

    public void registerUser(String email, String password) {
        User user = new User(email, password);

        AuthEndpoints endpoints = ServiceGenerator.getAuthEndpoints();

        Call<User> call = endpoints.registerUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                System.out.println("New User Registered");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("Registration failed");
            }
        });

    }

    public MutableLiveData<Boolean> getIsValidating() {
        return isValidating;
    }
}
