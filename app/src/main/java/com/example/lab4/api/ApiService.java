package com.example.lab4.api;

import com.example.lab4.model.MyModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiService {

    @GET("/services/rest/?method=flickr.favorites.getList&api_key=f7cb410bd4405796701669301d4f5c73&user_id=189411866%40N03&extras=views%2C+media%2C+path_alias%2C+url_sq%2C+url_t%2C+url_s%2C+url_q%2C+url_m%2C+url_n%2C+url_z%2C+url_c%2C+url_l%2C+url_o&per_page=30&page=1&format=json&nojsoncallback=1")
    Call<MyModel> getData(@Query("page") String page);

    @GET("/services/rest/?method=flickr.galleries.getPhotos&api_key=41de2f631e450643c7933eb12a0c1242&per_page=&extras=views%2C+media%2C+path_alias%2C+url_sq%2C+url_t%2C+url_s%2C+url_q%2C+url_m%2C+url_n%2C+url_z%2C+url_c%2C+url_l%2C+url_o&format=json&nojsoncallback=1")
    Call<MyModel> getCategory(@Query("gallery_id") String id);
}
