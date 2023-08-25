package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.models.NewsHeadlines;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class NewsDetailActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{

    private boolean isHideToolbarView = false;
    NewsHeadlines newsHeadlines;
    ImageView imageView;
    TextView title;
    TextView author;
    TextView desc;
    TextView time;
    TextView date;
    TextView source;







    @SuppressLint({"SetTextI18n", "CheckResult"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);



        imageView = findViewById(R.id.img);
        title = findViewById(R.id.title);
        author=findViewById(R.id.author);
        desc=findViewById(R.id.desc);
        date=findViewById(R.id.publishedAt);
        source=findViewById(R.id.source);

        newsHeadlines = (NewsHeadlines) getIntent().getSerializableExtra("data");




        title.setText(newsHeadlines.getTitle());
        time.setText(newsHeadlines.getPublishedAt());
        author.setText(newsHeadlines.getAuthor());
        desc.setText(newsHeadlines.getDescription());
        date.setText(newsHeadlines.getDescription());
        Picasso.get().load(newsHeadlines.getUrltoImage()).into(imageView);


        RequestOptions requestOptions = new RequestOptions();
        requestOptions.error(Utils.getRandomDrawbleColor());

    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

        if (percentage == 1f && isHideToolbarView) {
            isHideToolbarView = !isHideToolbarView;

        } else if (percentage < 1f && !isHideToolbarView) {
            isHideToolbarView = !isHideToolbarView;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_news, menu);
        return true;
    }

}
