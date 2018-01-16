package com.example.android.navdrawer.api;

import com.example.android.navdrawer.Model.PhotoModel;
import com.example.android.navdrawer.Model.Photos;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by pc on 1/16/2018.
 */

public interface ApiService {
    @GET("photos?"+ApiConstants.ConsumerKey)
    Call<PhotoModel> getPhotos(@Query("feature") String featureString);

    @FormUrlEncoded
    @POST("photos")
    Call<String> uploadPhoto(@Field("name") String stringName,@Body Photos photo);
}
