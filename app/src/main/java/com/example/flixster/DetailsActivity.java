package com.example.flixster;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.models.Movie;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;

import okhttp3.Headers;

public class DetailsActivity extends YouTubeBaseActivity {

    private static final String YOUTUBE_API_KEY = "AIzaSyCCV_arBwT36acrzIskdCasoIowrHxo4RM";
    public static final String VIDEOS_URL = "https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

    TextView d_title;
    TextView d_overview;
    TextView d_date;
    RatingBar d_rating;
    YouTubePlayerView ytPlayerView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //Import movie
        Movie movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));

        //Set Details Components
        d_title = (TextView) findViewById(R.id.detailTitle) ;
        d_title.setText(movie.getTitle());
        d_overview = (TextView) findViewById(R.id.detailOverview);
        d_overview.setText(movie.getOverview());
        d_date = (TextView) findViewById(R.id.detailDate);
        d_date.setText("Release Date: "+movie.getReleaseDate());
        d_rating = (RatingBar) findViewById(R.id.detailRating);
        d_rating.setRating(movie.getRating());
        //Get Video Data
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(String.format(VIDEOS_URL, movie.getMovieId()), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                try {
                    JSONArray results = json.jsonObject.getJSONArray("results");
                    if(results.length() == 0){
                        return;
                    }
                    String youtubeKey = results.getJSONObject(0).getString("key");
                    initializeYoutube(youtubeKey);
                } catch (JSONException e){
                    Log.e("DetailsActivity", "Failed to parse JSON", e);
                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.e("DetailsActivity", "Failed to make API call", throwable);
            }
        });

    }

    private void initializeYoutube(String youtubeKey) {
        //Set YouTube player
        ytPlayerView = (YouTubePlayerView) findViewById(R.id.player);
        ytPlayerView.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo(youtubeKey);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }

        });
    }

}
