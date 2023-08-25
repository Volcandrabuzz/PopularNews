package com.example.myapplication;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.models.NewsApiResponse;
import com.example.myapplication.models.NewsHeadlines;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener {

    private RecyclerView newsrv;
    private NewsAdapter newsadapter;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog=new ProgressDialog(this);
        dialog.setTitle("Fetching News Articles...");
        dialog.show();

        Requestmanager manager = new Requestmanager(this);
        manager.getNewsHeadlines(listener,"general",null);



    }

    private final onFetchDataListener<NewsApiResponse> listener=new onFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {
            showNews(list);
            dialog.dismiss();
            Toast.makeText(MainActivity.this,"All right",Toast.LENGTH_SHORT).show();


        }

        @Override
        public void onError(String message) {
        }
    };

    private void showNews(List<NewsHeadlines> list) {
        newsrv=findViewById(R.id.VrecyclerView);
        newsrv.setHasFixedSize(true);
        newsrv.setLayoutManager(new GridLayoutManager(this,1));

        newsadapter=new NewsAdapter(list,this,this);
        newsrv.setAdapter(newsadapter);
        newsadapter.notifyDataSetChanged();
    }



    @Override
    public void onNewsClicked(NewsHeadlines newsHeadlines) {
        startActivity(new Intent(MainActivity.this,NewsDetailActivity.class)
                .putExtra("data", newsHeadlines));

    }
}
