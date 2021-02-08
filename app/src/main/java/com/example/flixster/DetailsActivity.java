package com.example.flixster;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailsActivity extends AppCompatActivity {
    TextView d_title;
    TextView d_overview;
    RatingBar d_rating;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String title = getIntent().getStringExtra("title");
        String overview = getIntent().getStringExtra("overview");
        float rating = getIntent().getFloatExtra("rating", (float)0.0);

        //Set Details Components
        d_title = (TextView) findViewById(R.id.detailTitle) ;
        d_title.setText(title);
        d_overview = (TextView) findViewById(R.id.detailOverview);
        d_overview.setText(overview);
        d_rating = (RatingBar) findViewById(R.id.detailRating);
        d_rating.setRating(rating);
    }

}
