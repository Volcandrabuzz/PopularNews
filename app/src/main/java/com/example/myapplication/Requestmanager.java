package com.example.myapplication;

import android.content.Context;
import android.widget.Toast;

import com.example.myapplication.models.NewsApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class Requestmanager {
    Context context;
    Retrofit retrofit=new Retrofit.Builder().baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void getNewsHeadlines(onFetchDataListener listener, String category, String query){
        CallNewsApi callNewsApi=retrofit.create(CallNewsApi.class);
        Call<NewsApiResponse> call=callNewsApi.callHeadlines("us",category,query,context.getString(R.string.API_KEY));

        try{
            call.enqueue(new Callback<NewsApiResponse>() {
                @Override
                public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(context,"Error!!",Toast.LENGTH_SHORT).show();
                    }
                    listener.onFetchData(response.body().getArticle() ,response.message());

                }

                @Override
                public void onFailure(Call<NewsApiResponse> call, Throwable t) {
                    listener.onError("Request Failed !");

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public Requestmanager(Context context){this.context=context;}
    public interface CallNewsApi{
        @GET("top-headlines")
        Call<NewsApiResponse> callHeadlines(
                @Query("country") String country ,
                @Query("q") String query,
                @Query("category") String category,
                @Query("apiKey") String apiKey




        );
    }

}
