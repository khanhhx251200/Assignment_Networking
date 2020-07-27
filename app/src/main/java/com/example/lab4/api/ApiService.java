package com.example.lab4.api;

import com.example.lab4.model.MyModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiService {

    @GET("/services/rest/?method=flickr.favorites.getList&api_key=f7cb410bd4405796701669301d4f5c73&user_id=189411866%40N03&extras=views%2C+media%2C+path_alias%2C+url_sq%2C+url_t%2C+url_s%2C+url_q%2C+url_m%2C+url_n%2C+url_z%2C+url_c%2C+url_l%2C+url_o&per_page=30&page=1&format=json&nojsoncallback=1")
    Call<MyModel> getData(@Query("page") String page);
//
}
