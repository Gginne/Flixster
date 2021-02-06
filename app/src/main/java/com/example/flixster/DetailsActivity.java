package com.example.flixster;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {
    String title;
    String overview;
    String poster;
    float rating;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        title = getIntent().getStringExtra("title");
        overview = getIntent().getStringExtra("rating");
        poster = getIntent().getStringExtra("poster");
        rating = getIntent().getFloatExtra("rating", 0.0);
    }

}
