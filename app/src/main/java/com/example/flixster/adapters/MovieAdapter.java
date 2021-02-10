package com.example.flixster.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.DetailsActivity;
import com.example.flixster.MainActivity;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies){
        this.context = context;
        this.movies = movies;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder((movieView));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.bind(movie);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailsActivity.class);
                //Pass Data To Actvity
                i.putExtra("movie", Parcels.wrap(movie));
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity) context, (View)holder.poster,"profile");
                context.startActivity(i, options.toBundle());
            }

        });
    }



    @Override
    public int getItemCount() {
        return this.movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView overview;
        ImageView poster;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.movieTitle);
            overview = itemView.findViewById(R.id.movieOverview);
            poster = itemView.findViewById(R.id.moviePoster);

        }

        public void bind(Movie movie) {
            title.setText(movie.getTitle());
            overview.setText(movie.getOverview());
            String imageUrl;
            //Dynamically set image to poster or backdrop depending on phone orientation
            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                imageUrl = movie.getBackdropPath();
            } else {
                imageUrl = movie.getPosterPath();
            }
            Glide.with(context)
                    .load(imageUrl)
                    .placeholder(R.drawable.placeholder_poster)
                    .into(poster);
        }
    }
}
