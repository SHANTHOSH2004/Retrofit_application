package com.example.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitService {
    @GET("posts")
    Call<List<Example>> getPosts();


    @POST("posts")
    Call<PostModel> sentData(@Body PostModel postModel);
}

