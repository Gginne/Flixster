package com.example.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    String posterPath;
    String backdropPath;
    String title;
    String overview;
    double rating;

    public Movie(JSONObject jsonObject) throws JSONException {
        posterPath = jsonObject.getString("poster_path");
        backdropPath = jsonObject.getString("backdrop_path");
        title = jsonObject.getString("title");
        rating = jsonObject.getDouble("vote_average");
        overview = jsonObject.getString("overview");
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",posterPath);
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w780/%s",backdropPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public float getRating() {
        return (float)rating;
    }

    public static List<Movie> fromJSONArray(JSONArray movieArr) throws JSONException{
        List<Movie> movies = new ArrayList<>();
        for(int i = 0; i < movieArr.length(); i++){
            movies.add(new Movie(movieArr.getJSONObject(i)));
        }
        return movies;
    }

}
